/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.gaming.atom.engine;

import net.vpc.gaming.atom.model.Sprite;

import java.io.Serializable;

/**
 * A Task is the "Business" part of a sprite that handles
 * the behavior of a sprite all over the time.
 * <p/>
 * Tasks have special method <code>nextFrame</code> that's invoked
 * every frame to update sprite model when time evolves
 * <p/>
 * The Simplest Way to implement a SpriteTask is to extend <code>DefaultSpriteTask</code>.
 * <p/>
 * If the task should move the sprite consider implementing <code>MotionSpriteTask</code>.
 * <p>
 * Example :
 * </p>
 * <p/>
 * The Following is a simple Task that moves to the Right one Unit each frame step :
 * ie : The sprite will  perform an horizontal move to the EAST with the velocity 1/FPS
 * <p/>
 * <pre>
 * public class MoveRightTask implements SpriteTask {
 *    public public boolean nextFrame(SceneEngine scene,Sprite sprite){
 *       DPoint p=getSprite().getLocation();
 *       sprite.setLocation(new DPoint(p.getX()+1,p.getY()));
 *       return false;
 *    }
 * }
 * </pre>
 *
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
public interface SpriteTask extends Serializable {

    /*
     * called each frame step. must update sprite model (passed through setSprite method)
     * For instance, a move task should update sprite position according to the velocity of the sprite
     *
     * @return false is the task is terminated, false otherwise
     */
    public boolean nextFrame(SceneEngine scene, Sprite sprite);

}
