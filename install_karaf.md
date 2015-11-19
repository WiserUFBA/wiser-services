# Install Basic Karaf + Cxf + WebConsole + Wrapper(init scripts) + DriverMQTT-OSGi
### PS.: need to found a way to use SSH KEY in installation

To configure Karaf, you need to edit some files, and install some bundles:

### Edit these files

All main configuration of Karaf is located in `/etc`, the most used files are:

```sh
keys.properties               # Some keys
config.properties             # Configuration of karaf
org.apache.karaf.shell.cfg    # Some shell config
org.ops4j.pax.url.mvn.cfg     # Maven Urls
profile.cfg                   # System Profiles 
shell.init.script             # Example Init Script, the default in KARAF
startup.properties            # Start properties -> Used in the Java enviromment 
system.properties             # Sytem properties -> Used in the Java enviromment
users.properties              # User properties  -> Used in the Java enviromment
```

For basic and secure installation we will only change 3 files, theese files are:
`org.ops4j.pax.url.mvn.cfg, users.properties, org.apache.karaf.shell.cfg`

##### 1º org.ops4j.pax.url.mvn.cfg

Add this line after `org.ops4j.pax.url.mvn.repositories= \`:

```sh
    https://github.com/WiserUFBA/wiser-mvn-repo/raw/master/releases@id=wiser, \
```
##### 2º users.properties

Change `karaf` password, and username, or create new account, you choose:

```ssh
# Example
karaf = boteco@dmin,_g_:admingroup
```

##### 3º org.apache.karaf.shell.cfg

Change ssh port of 8101 to 8181:

```sh
sshPort = 8118
```

##### Script way

You can avoid these manual edit and done this quickly with the following script

```sh
# Enter Karaf /etc Folder
cd etc/
# Do Step 1
sed -i '/org.ops4j.pax.url.mvn.repositories=/a\ \ \ \ https://github.com/WiserUFBA/wiser-mvn-repo/raw/master/releases@id=wiser, \\' org.ops4j.pax.url.mvn.cfg
# Do Step 2
sed -i '/karaf = /c\karaf = boteco@dmin,_g_:admingroup' users.properties
# Do Step 3
sed -i '/sshPort = /c\sshPort = 8118' org.apache.karaf.shell.cfg
```

#### To install the necessary bundles

Use the following command to install the necessary bundles:

```sh
ssh -p 8118 karaf@localhost "feature:repo-add cxf 3.1.4;feature:install cxf;feature:install webconsole;feature:install wrapper;bundle:install mvn:br.ufba.dcc.wiser/drivermqtt-osgi/2.1.4;bundle:start DriverMQTT-OSGi;wrapper:install"
```

### Complete script to install karaf

```sh
# Enter Karaf /etc Folder
cd etc/
# Do Step 1
sed -i '/org.ops4j.pax.url.mvn.repositories=/a\ \ \ \ https://github.com/WiserUFBA/wiser-mvn-repo/raw/master/releases@id=wiser, \\' org.ops4j.pax.url.mvn.cfg
# Do Step 2
sed -i '/karaf = /c\karaf = boteco@dmin,_g_:admingroup' users.properties
# Do Step 3
sed -i '/sshPort = /c\sshPort = 8118' org.apache.karaf.shell.cfg
# Configure and install the karaf dependencies
ssh -p 8118 karaf@localhost "feature:repo-add cxf 3.1.4;feature:install cxf;feature:install webconsole;feature:install wrapper;bundle:install mvn:br.ufba.dcc.wiser/drivermqtt-osgi/2.1.4;bundle:start DriverMQTT-OSGi;wrapper:install"

# !!! LAST STEP ADD KARAF TO BOOT !!!
# ~> Not Implemented
```
