pwd
JDK_HOME="C:\Program Files\Java\JDK7.0"
P_SRC="..\..\..\..\..\..\..\..\..\"
P_PKG="tn.edu.eniso.pong.main.shared.dal.corba.generated"

%JDK_HOME%\bin\idlj.exe -td %P_SRC% -fall -pkgTranslate "ConnectorCorba" %P_PKG% DalCorba.idl
