package tn.edu.eniso.pong.main.shared.dal.corba.generated;


/**
 * tn/edu/eniso/pong/main/shared/dal/corba/generated/ModelCorbaHelper.java .
 * Generated by the IDL-to-Java compiler (portable), version "3.2"
 * from DalCorba.idl
 * Tuesday, December 20, 2011 11:10:02 PM CET
 */

abstract public class ModelCorbaHelper {
    private static String _id = "IDL:ConnectorCorba/ModelCorba:1.0";

    public static void insert(org.omg.CORBA.Any a, tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorba that) {
        org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
        a.type(type());
        write(out, that);
        a.read_value(out.create_input_stream(), type());
    }

    public static tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorba extract(org.omg.CORBA.Any a) {
        return read(a.create_input_stream());
    }

    private static org.omg.CORBA.TypeCode __typeCode = null;
    private static boolean __active = false;

    synchronized public static org.omg.CORBA.TypeCode type() {
        if (__typeCode == null) {
            synchronized (org.omg.CORBA.TypeCode.class) {
                if (__typeCode == null) {
                    if (__active) {
                        return org.omg.CORBA.ORB.init().create_recursive_tc(_id);
                    }
                    __active = true;
                    org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember[4];
                    org.omg.CORBA.TypeCode _tcOf_members0 = null;
                    _tcOf_members0 = org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.tk_longlong);
                    _members0[0] = new org.omg.CORBA.StructMember(
                            "frame",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.type();
                    _members0[1] = new org.omg.CORBA.StructMember(
                            "ball",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.type();
                    _members0[2] = new org.omg.CORBA.StructMember(
                            "paddle1",
                            _tcOf_members0,
                            null);
                    _tcOf_members0 = tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.type();
                    _members0[3] = new org.omg.CORBA.StructMember(
                            "paddle2",
                            _tcOf_members0,
                            null);
                    __typeCode = org.omg.CORBA.ORB.init().create_struct_tc(tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorbaHelper.id(), "ModelCorba", _members0);
                    __active = false;
                }
            }
        }
        return __typeCode;
    }

    public static String id() {
        return _id;
    }

    public static tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorba read(org.omg.CORBA.portable.InputStream istream) {
        tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorba value = new tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorba();
        value.frame = istream.read_longlong();
        value.ball = tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.read(istream);
        value.paddle1 = tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.read(istream);
        value.paddle2 = tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.read(istream);
        return value;
    }

    public static void write(org.omg.CORBA.portable.OutputStream ostream, tn.edu.eniso.pong.main.shared.dal.corba.generated.ModelCorba value) {
        ostream.write_longlong(value.frame);
        tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.write(ostream, value.ball);
        tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.write(ostream, value.paddle1);
        tn.edu.eniso.pong.main.shared.dal.corba.generated.SpriteCorbaHelper.write(ostream, value.paddle2);
    }

}
