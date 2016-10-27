/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.gaming.atom.presentation;

/**
 * Event Controller that handle both Key events and Mouse Events
 *
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
public interface SceneController {
    public static final int KEY_TYPED = 1 << 0;
    public static final int KEY_PRESSED = 1 << 1;
    public static final int KEY_RELEASED = 1 << 2;

    public static final int MOUSE_CLICKED = 1 << 4;
    public static final int MOUSE_PRESSED = 1 << 5;
    public static final int MOUSE_RELEASED = 1 << 6;
    public static final int MOUSE_ENTERED = 1 << 7;
    public static final int MOUSE_EXITED = 1 << 8;
    public static final int MOUSE_DRAGGED = 1 << 9;
    public static final int MOUSE_MOVED = 1 << 10;

    public boolean acceptEvent(int event);

    /**
     * Invoked when a key has been typed. See the class description for
     * {@link SceneKeyEvent} for a definition of a key typed event.
     */
    public void keyTyped(SceneKeyEvent e);

    /**
     * Invoked when a key has been pressed. See the class description for
     * {@link SceneKeyEvent} for a definition of a key pressed event.
     */
    public void keyPressed(SceneKeyEvent e);


    /**
     * Invoked when a key has been released. See the class description for
     * {@link SceneKeyEvent} for a definition of a key released event.
     */
    public void keyReleased(SceneKeyEvent e);

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on
     * a component.
     */
    public void mouseClicked(SceneMouseEvent e);

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(SceneMouseEvent e);

    /**
     * Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(SceneMouseEvent e);

    /**
     * Invoked when the mouse enters a component.
     */
    public void mouseEntered(SceneMouseEvent e);

    /**
     * Invoked when the mouse exits a component.
     */
    public void mouseExited(SceneMouseEvent e);

    /**
     * Invoked when a mouse button is pressed on a component and then dragged.
     * <code>MOUSE_DRAGGED</code> events will continue to be delivered to the
     * component where the drag originated until the mouse button is released
     * (regardless of whether the mouse position is within the bounds of the
     * component).
     * <p/>
     * Due to platform-dependent Drag&Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&Drop operation.
     */
    public void mouseDragged(SceneMouseEvent e);

    /**
     * Invoked when the mouse cursor has been moved onto a component but no
     * buttons have been pushed.
     */
    public void mouseMoved(SceneMouseEvent e);

    public void sceneInitialized(Scene scene);

    public void sceneStarted(Scene scene);

    public void sceneStopped(Scene scene);

    public void nexFrame(Scene scene);
}
