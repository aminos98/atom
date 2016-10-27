package net.vpc.gaming.atom.presentation;

import net.vpc.gaming.atom.model.Sprite;

import java.awt.*;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 * @creationtime 5 dec. 2006 10:56:13
 */
public interface SpriteView {

    public void draw(SpriteDrawingContext context);

    public Shape getShape(Sprite sprite, Scene view);

//    public boolean isIsometric(Sprite sprite, Scene view);
//
//    public SceneLayoutType getSceneLayoutType();

    public SpriteViewConstraints getSpriteViewConstraints(Sprite sprite);
}
