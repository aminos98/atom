package tn.edu.eniso.pong.main.shared.dal.corba.generated;


/**
 * tn/edu/eniso/pong/main/shared/dal/corba/generated/SpriteCorba.java .
 * Generated by the IDL-to-Java compiler (portable), version "3.2"
 * from DalCorba.idl
 * Tuesday, December 20, 2011 11:10:02 PM CET
 */

public final class SpriteCorba implements org.omg.CORBA.portable.IDLEntity {
    public int life = (int) 0;
    public double x = (double) 0;
    public double y = (double) 0;
    public double direction = (double) 0;
    public double speed = (double) 0;

    public SpriteCorba() {
    } // ctor

    public SpriteCorba(int _life, double _x, double _y, double _direction, double _speed) {
        life = _life;
        x = _x;
        y = _y;
        direction = _direction;
        speed = _speed;
    } // ctor

} // class SpriteCorba
