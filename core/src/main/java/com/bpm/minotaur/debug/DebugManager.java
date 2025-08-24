package com.bpm.minotaur.debug;

/**
 * Manages the state of all debugging tools and overlays.
 * This class provides a central point to enable or disable debug features.
 */
public class DebugManager {

    // A static instance to ensure there's only one DebugManager (Singleton pattern).
    public static final DebugManager INSTANCE = new DebugManager();

    private boolean isDebugOverlayVisible = false;
    private boolean isGodMode = false;

    /**
     * Private constructor to prevent creating multiple instances.
     */
    private DebugManager() {
    }

    public boolean isDebugOverlayVisible() {
        return isDebugOverlayVisible;
    }

    public void toggleDebugOverlay() {
        isDebugOverlayVisible = !isDebugOverlayVisible;
    }

    public boolean isGodMode() {
        return isGodMode;
    }

    public void setGodMode(boolean godMode) {
        isGodMode = godMode;
    }
}
