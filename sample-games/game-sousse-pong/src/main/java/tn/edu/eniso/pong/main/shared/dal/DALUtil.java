/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.eniso.pong.main.shared.dal;

import net.vpc.gaming.atom.engine.SceneEngine;
import net.vpc.gaming.atom.model.SceneEngineModel;
import net.vpc.gaming.atom.model.Sprite;
import tn.edu.eniso.pong.main.shared.dal.model.DALStructModel;
import tn.edu.eniso.pong.main.shared.dal.model.DALStructSprite;
import tn.edu.eniso.pong.main.shared.model.Ball;
import tn.edu.eniso.pong.main.shared.model.Paddle;

/**
 * @author Taha Ben Salah (taha.bensalah@gmail.com)
 */
public class DALUtil {

    public static DALStructModel toDALStructModel(SceneEngine sceneEngine) {
        DALStructModel data = new DALStructModel();
        data.frame = sceneEngine.getFrame();
        data.ball = toDAL(sceneEngine.findSprite(Ball.class));
        data.paddle1 = toDAL(sceneEngine.findSpriteByPlayer(Paddle.class, 1));
        data.paddle2 = toDAL(sceneEngine.findSpriteByPlayer(Paddle.class, 2));
        return data;
    }

    public static DALStructSprite toDAL(Sprite s) {
        if (s == null) {
            return null;
        }
        return new DALStructSprite(s.getLife(), s.getLocation().getX(), s.getLocation().getY(), s.getDirection(), s.getSpeed());
    }

}
