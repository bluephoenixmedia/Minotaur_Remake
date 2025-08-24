package com.bpm.minotaur.sound;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.Gdx;
import java.util.Random;

public class AY3914SoundChip {
    private static final int SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = 1024;

    // Volume table - logarithmic scale like the real chip
    private static final float[] VOLUME_TABLE = {
        0.0f, 0.01f, 0.0141f, 0.02f, 0.0283f, 0.04f, 0.0566f, 0.08f,
        0.1131f, 0.16f, 0.2263f, 0.32f, 0.4525f, 0.64f, 0.905f, 1.0f
    };

    private AudioDevice audioDevice;
    private Thread audioThread;
    private boolean running = false;

    // Channel state
    private Channel[] channels = new Channel[3];

    // Noise generator state
    private int noiseCounter = 0;
    private int noisePeriod = 1;
    private int noiseShift = 1;
    private boolean noiseOutput = false;

    public static class Channel {
        public int tonePeriod = 1;
        public int toneCounter = 0;
        public boolean toneOutput = false;
        public int volume = 0;
        public boolean toneEnable = true;
        public boolean noiseEnable = true;
        public boolean useEnvelope = false;
    }

    public AY3914SoundChip() {
        for (int i = 0; i < 3; i++) {
            channels[i] = new Channel();
        }

        audioDevice = Gdx.audio.newAudioDevice(SAMPLE_RATE, false);
        startAudioThread();
    }

    /**
     * Set tone frequency for a channel
     * @param channel Channel number (0-2)
     * @param frequency Frequency in Hz
     */
    public void setToneFrequency(int channel, float frequency) {
        if (channel < 0 || channel > 2) return;

        // Convert frequency to period (chip uses period values)
        // Formula: period = clockFreq / (16 * frequency)
        // Intellivision uses ~1.79MHz clock
        int period = Math.max(1, (int)(1789773 / (16 * frequency)));
        channels[channel].tonePeriod = Math.min(4095, period); // 12-bit max
    }

    /**
     * Set volume for a channel
     * @param channel Channel number (0-2)
     * @param volume Volume level (0-15)
     */
    public void setVolume(int channel, int volume) {
        if (channel < 0 || channel > 2) return;
        channels[channel].volume = Math.max(0, Math.min(15, volume));
    }

    /**
     * Set noise frequency
     * @param frequency Frequency in Hz
     */
    public void setNoiseFrequency(float frequency) {
        int period = Math.max(1, (int)(1789773 / (16 * frequency)));
        noisePeriod = Math.min(31, period); // 5-bit max
    }

    /**
     * Enable/disable tone for a channel
     */
    public void setToneEnable(int channel, boolean enable) {
        if (channel < 0 || channel > 2) return;
        channels[channel].toneEnable = enable;
    }

    /**
     * Enable/disable noise for a channel
     */
    public void setNoiseEnable(int channel, boolean enable) {
        if (channel < 0 || channel > 2) return;
        channels[channel].noiseEnable = enable;
    }

    /**
     * Play a simple beep sound
     */
    public void playBeep(int channel, float frequency, int volume, float duration) {
        setToneFrequency(channel, frequency);
        setVolume(channel, volume);
        setToneEnable(channel, true);
        setNoiseEnable(channel, false);

        // Schedule to turn off after duration
        new Thread(() -> {
            try {
                Thread.sleep((long)(duration * 1000));
                setVolume(channel, 0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Play noise sound
     */
    public void playNoise(int channel, float frequency, int volume, float duration) {
        setNoiseFrequency(frequency);
        setVolume(channel, volume);
        setToneEnable(channel, false);
        setNoiseEnable(channel, true);

        new Thread(() -> {
            try {
                Thread.sleep((long)(duration * 1000));
                setVolume(channel, 0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void startAudioThread() {
        running = true;
        audioThread = new Thread(() -> {
            float[] buffer = new float[BUFFER_SIZE];
            int clockDivider = SAMPLE_RATE * 16 / 1789773; // Approximate clock scaling

            while (running) {
                generateSamples(buffer, clockDivider);
                audioDevice.writeSamples(buffer, 0, BUFFER_SIZE);
            }
        });
        audioThread.setDaemon(true);
        audioThread.start();
    }

    private void generateSamples(float[] buffer, int clockDivider) {
        for (int i = 0; i < BUFFER_SIZE; i++) {
            float sample = 0;

            // Update noise generator
            updateNoise();

            // Process each channel
            for (int ch = 0; ch < 3; ch++) {
                Channel channel = channels[ch];

                // Update tone generator
                updateTone(channel);

                // Calculate channel output
                boolean toneOut = channel.toneEnable ? channel.toneOutput : true;
                boolean noiseOut = channel.noiseEnable ? noiseOutput : true;
                boolean channelOut = toneOut && noiseOut;

                if (channelOut) {
                    sample += VOLUME_TABLE[channel.volume];
                }
            }

            // Scale and clamp output
            buffer[i] = Math.max(-1.0f, Math.min(1.0f, sample * 0.3f));
        }
    }

    private void updateTone(Channel channel) {
        channel.toneCounter--;
        if (channel.toneCounter <= 0) {
            channel.toneCounter = channel.tonePeriod;
            channel.toneOutput = !channel.toneOutput;
        }
    }

    private void updateNoise() {
        noiseCounter--;
        if (noiseCounter <= 0) {
            noiseCounter = noisePeriod;

            // 17-bit Linear Feedback Shift Register
            int bit = ((noiseShift >> 0) ^ (noiseShift >> 3)) & 1;
            noiseShift = (noiseShift >> 1) | (bit << 16);
            noiseOutput = (noiseShift & 1) != 0;
        }
    }

    /**
     * Convenience method to play classic Intellivision-style sounds
     */
    public void playClassicSound(String type) {
        switch (type.toLowerCase()) {
            case "startup":
                // Classic ascending beep
                playBeep(0, 220, 12, 0.2f);
                new Thread(() -> {
                    try {
                        Thread.sleep(200);
                        playBeep(1, 330, 12, 0.2f);
                        Thread.sleep(200);
                        playBeep(2, 440, 12, 0.3f);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
                break;

            case "explosion":
                // Noise-based explosion
                playNoise(0, 100, 15, 0.5f);
                playNoise(1, 150, 12, 0.4f);
                break;

            case "laser":
                // Descending tone
                setToneFrequency(0, 800);
                setVolume(0, 10);
                setToneEnable(0, true);
                setNoiseEnable(0, false);

                new Thread(() -> {
                    for (int i = 0; i < 20; i++) {
                        setToneFrequency(0, 800 - i * 30);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                    setVolume(0, 0);
                }).start();
                break;

            case "menu":
                // Simple menu beep
                playBeep(0, 500, 8, 0.1f);
                break;
        }
    }

    public void dispose() {
        running = false;
        if (audioThread != null) {
            try {
                audioThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (audioDevice != null) {
            audioDevice.dispose();
        }
    }
}
