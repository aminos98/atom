package jcanon;


/**
* jcanon/CorbaFire.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/vpc/xprojects/games/canon/src/tn/rnu/enit/ateliercorba/jcanon/jcanon.idl
* lundi 15 octobre 2007 19 h 43 CEST
*/

public final class CorbaFire implements org.omg.CORBA.portable.IDLEntity
{
  public int player = (int)0;
  public int x = (int)0;
  public int y = (int)0;
  public int mood = (int)0;

  public CorbaFire ()
  {
  } // ctor

  public CorbaFire (int _player, int _x, int _y, int _mood)
  {
    player = _player;
    x = _x;
    y = _y;
    mood = _mood;
  } // ctor

} // class CorbaFire