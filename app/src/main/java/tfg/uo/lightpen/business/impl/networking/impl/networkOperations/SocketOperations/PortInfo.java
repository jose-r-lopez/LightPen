package tfg.uo.lightpen.business.impl.networking.impl.networkOperations.SocketOperations;

import java.util.HashMap;

/**
 * This class holds the information about the most scanned ports worldwide, extracted from here:
 * <p>
 * https://docs.google.com/spreadsheets/d/1r_IriqmkTNPSTiUwii_hQ8Gwl2tfTUz8AGIOIL-wMIE/pub?output=html
 * <p>
 * It only contains ports scanned with a frequency up to 0,005% from the total.
 */
public class PortInfo {

    /**
     * Raw TCP port info
     */
    private static String TCPMostScannedPortsRawInfo = "http;80;0,484143;# World Wide Web HTTP\n" +
            "telnet;23;0,221265;\n" +
            "https;443;0,208669;# secure http (SSL)\n" +
            "ftp;21;0,197667;# File Transfer [Control]\n" +
            "ssh;22;0,182286;# Secure Shell Login\n" +
            "smtp;25;0,131314;# Simple Mail Transfer\n" +
            "ms-wbt-server;3389;0,083904;# Microsoft Remote Display Protocol (aka ms-term-serv, microsoft-rdp) | MS WBT Server\n" +
            "pop3;110;0,077142;# PostOffice V.3 | Post Office Protocol - Version 3\n" +
            "microsoft-ds;445;0,056944;# SMB directly over IP\n" +
            "netbios-ssn;139;0,050809;# NETBIOS Session Service\n" +
            "imap;143;0,05042;# Interim Mail Access Protocol v2 | Internet Message Access Protocol\n" +
            "domain;53;0,048463;# Domain Name Server\n" +
            "msrpc;135;0,047798;# epmap | Microsoft RPC services | DCE endpoint resolution\n" +
            "mysql;3306;0,04539;\n" +
            "http-proxy;8080;0,042052;# http-alt | Common HTTP proxy/second web server port | HTTP Alternate (see port 80)\n" +
            "pptp;1723;0,032468;# Point-to-point tunnelling protocol\n" +
            "rpcbind;111;0,030034;# sunrpc | portmapper, rpcbind | SUN Remote Procedure Call\n" +
            "pop3s;995;0,029921;# POP3 protocol over TLS/SSL | pop3 protocol over TLS/SSL (was spop3)\n" +
            "imaps;993;0,027199;# imap4 protocol over TLS/SSL\n" +
            "vnc;5900;0,02356;# rfb | Virtual Network Computer display 0 | Remote Framebuffer\n" +
            "NFS-or-IIS;1025;0,022406;# blackjack | IIS, NFS, or listener RFS remote_file_sharing | network blackjack\n" +
            "submission;587;0,019721;# Message Submission\n" +
            "sun-answerbook;8888;0,016522;# ddi-udp-1 | ddi-tcp-1 | Sun Answerbook HTTP server. Or gnump3d streaming music server | NewsEDGE server TCP (TCP 1) | NewsEDGE server UDP (UDP 1)\n" +
            "smux;199;0,015945;# SNMP Unix Multiplexer\n" +
            "h323q931;1720;0,014277;# h323hostcall | Interactive media | H.323 Call Control Signalling | H.323 Call Control\n" +
            "smtps;465;0,013888;# igmpv3lite | urd | smtp protocol over TLS/SSL (was ssmtp) | URL Rendesvous Directory for SSM | IGMP over UDP for SSM\n" +
            "afp;548;0,012395;# afpovertcp | AFP over TCP\n" +
            "ident;113;0,01237;# auth | ident, tap, Authentication Service | Authentication Service\n" +
            "hosts2-ns;81;0,012056;# HOSTS2 Name Server\n" +
            "X11:1;6001;0,01173;# X Window server\n" +
            "snet-sensor-mgmt;10000;0,011692;# ndmp | SecureNet Pro Sensor https management server or apple airport admin | Network Data Management Protocol\n" +
            "shell;514;0,011078;# syslog | BSD rshd(8) | cmd like exec, but automatic authentication is performed as for login server\n" +
            "sip;5060;0,010613;# Session Initiation Protocol (SIP)\n" +
            "bgp;179;0,010538;# Border Gateway Protocol\n" +
            "LSA-or-nterm;1026;0,010237;# cap | nterm remote_login network_terminal | Calendar Access Protocol\n" +
            "cisco-sccp;2000;0,010112;# cisco SCCP (Skinny Client Control Protocol) | Cisco SCCP | Cisco SCCp\n" +
            "https-alt;8443;0,009986;# pcsync-https | Common alternative https port | PCsync HTTPS\n" +
            "http-alt;8000;0,00971;# irdmi | A common alternative http port | iRDMI\n" +
            "filenet-tms;32768;0,009199;# Filenet TMS\n" +
            "rtsp;554;0,008104;# Real Time Stream Control Protocol | Real Time Streaming Protocol (RTSP)\n" +
            "rsftp;26;0,007991;# RSFTP\n" +
            "ms-sql-s;1433;0,007929;# Microsoft-SQL-Server\n" +
            "unknown;49152;0,007907;\n" +
            "dc;2001;0,007339;# wizard | or nfr20 web queries | curry\n" +
            "printer;515;0,007214;# spooler (lpd) | spooler\n" +
            "http;8008;0,006843;# http-alt | IBM HTTP server | HTTP Alternate\n" +
            "unknown;49154;0,006767;\n" +
            "IIS;1027;0,006724;# 6a44 | IPv6 Behind NAT44 CPEs\n" +
            "nrpe;5666;0,006614;# Nagios NRPE\n" +
            "ldp;646;0,006549;# Label Distribution\n" +
            "upnp;5000;0,006423;# commplex-main | Universal PnP, also Free Internet Chess Server\n" +
            "pcanywheredata;5631;0,006248;\n" +
            "ipp;631;0,00616;# Internet Printing Protocol -- for one implementation see http://www.cups.org (Common UNIX Printing System) | IPP (Internet Printing Protocol)\n" +
            "unknown;49153;0,006158;\n" +
            "blackice-icecap;8081;0,006147;# sunproxyadmin | ICECap user console | Sun Proxy Admin Service\n" +
            "nfs;2049;0,00611;# networked file system\n" +
            "kerberos-sec;88;0,006072;# kerberos | Kerberos (v5) | Kerberos\n" +
            "finger;79;0,006022;\n" +
            "vnc-http;5800;0,005947;# Virtual Network Computer HTTP Access, display 0\n" +
            "pop3pw;106;0,005934;# 3com-tsmux | Eudora compatible PW changer | 3COM-TSMUX\n" +
            "ccproxy-ftp;2121;0,005834;# scientia-ssdb | CCProxy FTP Proxy | SCIENTIA-SSDB\n" +
            "nfsd-status;1110;0,005809;# nfsd-keepalive | webadmstart | Cluster status info | Start web admin server | Client status info\n" +
            "unknown;49155;0,005702;\n" +
            "X11;6000;0,005683;# X Window server\n" +
            "login;513;0,005595;\"# who | BSD rlogind(8) | remote login a la telnet; automatic authentication performed based on priviledged port numbers and distributed data bases which identify \"\"authentication domains\"\" | maintains data bases showing who's logged in to machines on a local net and the load average of the machine\"\n" +
            "ftps;990;0,00557;# ftp protocol, control, over TLS/SSL\n" +
            "wsdapi;5357;0,005474;# Web Services for Devices\n" +
            "svrloc;427;0,005382;# Server Location\n" +
            "unknown;49156;0,005322;\n" +
            "klogin;543;0,005282;# Kerberos (v4/v5)\n" +
            "kshell;544;0,005269;# krcmd Kerberos (v4/v5) | krcmd\n" +
            "admdog;5101;0,005156;# talarian-udp | talarian-tcp | (chili!soft asp) | Talarian_TCP | Talarian_UDP\n";

    /**
     * Raw UDP port info
     */
    private static String UDPMostScannedPortsRawInfo = "ipp;631;0,450281;# Internet Printing Protocol\n" +
            "snmp;161;0,433467;# Simple Net Mgmt Proto\n" +
            "netbios-ns;137;0,365163;# NETBIOS Name Service\n" +
            "ntp;123;0,330879;# Network Time Protocol\n" +
            "netbios-dgm;138;0,29783;# NETBIOS Datagram Service\n" +
            "ms-sql-m;1434;0,293184;# Microsoft-SQL-Monitor\n" +
            "microsoft-ds;445;0,253118;\n" +
            "msrpc;135;0,244452;# Microsoft RPC services\n" +
            "dhcps;67;0,22801;# DHCP/Bootstrap Protocol Server\n" +
            "domain;53;0,213496;# Domain Name Server\n" +
            "netbios-ssn;139;0,193726;# NETBIOS Session Service\n" +
            "isakmp;500;0,163742;\n" +
            "dhcpc;68;0,140118;# DHCP/Bootstrap Protocol Client\n" +
            "route;520;0,139376;# router routed -- RIP\n" +
            "upnp;1900;0,136543;# Universal PnP\n" +
            "nat-t-ike;4500;0,124467;# IKE Nat Traversal negotiation (RFC3947)\n" +
            "syslog;514;0,119804;# BSD syslogd(8)\n" +
            "unknown;49152;0,116002;\n" +
            "snmptrap;162;0,103346;# snmp-trap\n" +
            "tftp;69;0,102835;# Trivial File Transfer\n" +
            "zeroconf;5353;0,100166;# Mac OS X Bonjour/Zeroconf port\n" +
            "rpcbind;111;0,093988;# portmapper, rpcbind\n" +
            "unknown;49154;0,092369;\n" +
            "L2TP;1701;0,076163;\n" +
            "puparp;998;0,073395;\n" +
            "vsinet;996;0,073362;\n" +
            "maitrd;997;0,073247;\n" +
            "applix;999;0,07323;# Applix ac\n" +
            "netassistant;3283;0,066072;# Apple Remote Desktop Net Assistant reporting feature\n" +
            "unknown;49153;0,060743;\n" +
            "radius;1812;0,053839;# RADIUS authentication protocol (RFC 2138)\n" +
            "profile;136;0,051862;# PROFILE Naming System\n" +
            "msantipiracy;2222;0,047902;# Microsoft Office OS X antipiracy network monitor\n" +
            "nfs;2049;0,044531;# networked file system\n" +
            "omad;32768;0,044407;# OpenMosix Autodiscovery Daemon\n" +
            "sip;5060;0,04435;# Session Initiation Protocol (SIP)\n" +
            "blackjack;1025;0,041813;# network blackjack\n" +
            "ms-sql-s;1433;0,036821;# Microsoft-SQL-Server\n" +
            "IISrpc-or-vat;3456;0,036607;# also VAT default data\n" +
            "http;80;0,035767;# World Wide Web HTTP\n" +
            "bakbonenetvault;20031;0,02549;# BakBone NetVault primary communications port\n" +
            "win-rpc;1026;0,024777;# Commonly used to send MS Messenger spam\n" +
            "echo;7;0,024679;\n" +
            "radacct;1646;0,023196;# radius accounting\n" +
            "radius;1645;0,02318;# radius authentication\n" +
            "http-rpc-epmap;593;0,022933;# HTTP RPC Ep Map\n" +
            "ntalk;518;0,022208;# (talkd)\n" +
            "dls-monitor;2048;0,021549;\n" +
            "serialnumberd;626;0,021473;# Mac OS X Server serial number (licensing) daemon\n" +
            "unknown;1027;0,019822;\n" +
            "xdmcp;177;0,018551;# X Display Manager Control Protocol\n" +
            "h323gatestat;1719;0,0185;# H.323 Gatestat\n" +
            "svrloc;427;0,01827;# Server Location\n" +
            "retrospect;497;0,017348;\n" +
            "krb524;4444;0,016343;\n" +
            "unknown;1023;0,016188;\n" +
            "unknown;65024;0,016064;\n" +
            "chargen;19;0,015865;# ttytst source Character Generator\n" +
            "discard;9;0,015733;# sink null\n" +
            "unknown;49193;0,015562;\n" +
            "solid-mux;1029;0,014536;# Solid Mux Server\n" +
            "tacacs;49;0,01402;# Login Host Protocol (TACACS)\n" +
            "kerberos-sec;88;0,013476;# Kerberos (v5)\n" +
            "ms-lsa;1028;0,013443;\n" +
            "wdbrpc;17185;0,013395;# vxWorks WDB remote debugging ONCRPC\n" +
            "h225gatedisc;1718;0,012554;# H.225 gatekeeper discovery\n" +
            "unknown;49186;0,01255;\n" +
            "cisco-sccp;2000;0,011697;# cisco SCCP (Skinny Client Control Protocol)\n" +
            "BackOrifice;31337;0,011469;# cDc Back Orifice remote admin tool\n" +
            "unknown;49192;0,011044;\n" +
            "unknown;49201;0,011044;\n" +
            "printer;515;0,011022;# spooler (lpd)\n" +
            "rockwell-csp2;2223;0,010902;# Rockwell CSP2\n" +
            "https;443;0,01084;\n" +
            "unknown;49181;0,010542;\n" +
            "radacct;1813;0,010429;# RADIUS accounting protocol (RFC 2139)\n" +
            "cfdptkt;120;0,010181;\n" +
            "pcmail-srv;158;0,010148;# PCMail Server\n" +
            "unknown;49200;0,01004;\n" +
            "adobeserver-3;3703;0,00958;# Adobe Server 3\n" +
            "unknown;32815;0,009322;\n" +
            "qotd;17;0,009209;# Quote of the Day\n" +
            "upnp;5000;0,008913;# also complex-main\n" +
            "sometimes-rpc6;32771;0,00849;# Sometimes an RPC port on my Solaris box (rusersd)\n" +
            "unknown;33281;0,008286;\n" +
            "iad1;1030;0,008007;# BBN IAD\n" +
            "asf-rmcp;623;0,007929;# ASF Remote Management and Control\n" +
            "exp2;1022;0,007929;# RFC3692-style Experiment 2 (*) [RFC4727]\n" +
            "filenet-rpc;32769;0,007768;# Filenet RPC\n" +
            "pcanywherestat;5632;0,007694;\n" +
            "ndmp;10000;0,007598;# Network Data Management Protocol\n" +
            "unknown;49156;0,00753;\n" +
            "unknown;49182;0,00753;\n" +
            "unknown;49191;0,00753;\n" +
            "unknown;49194;0,00753;\n" +
            "wap-wsp;9200;0,007268;# WAP connectionless session services\n" +
            "unknown;30718;0,00719;\n" +
            "unknown;49185;0,007028;\n" +
            "unknown;49188;0,007028;\n" +
            "unknown;49190;0,007028;\n" +
            "unknown;49211;0,007028;\n" +
            "commplex-link;5001;0,007018;\n" +
            "llmnr;5355;0,006938;# LLMNR\n" +
            "sometimes-rpc4;32770;0,006745;# Sometimes an RPC port on my Solaris box\n" +
            "unknown;34555;0,006732;\n" +
            "unknown;34861;0,006732;\n" +
            "unknown;37444;0,006732;\n" +
            "iad3;1032;0,006705;# BBN IAD\n" +
            "squid-ipc;3130;0,006656;\n" +
            "lockd;4045;0,006656;# NFS lock daemon/manager\n" +
            "iad2;1031;0,006639;# BBN IAD\n" +
            "unknown;49158;0,006526;\n" +
            "unknown;49196;0,006526;\n" +
            "time;37;0,006458;# timserver\n" +
            "symantec-av;2967;0,006425;# Symantec AntiVirus (rtvscan.exe)\n" +
            "icq;4000;0,006392;# AOL ICQ instant messaging clent-server communication\n" +
            "ftps-data;989;0,006277;# ftp protocol, data, over TLS/SSL\n" +
            "apple-sasl;3659;0,006277;# Apple SASL\n" +
            "rfa;4672;0,006227;# remote file access server\n" +
            "unknown;34862;0,006214;\n" +
            "telnet;23;0,006211;\n" +
            "unknown;49162;0,006024;\n" +
            "unknown;49187;0,006024;\n" +
            "unknown;49189;0,006024;\n" +
            "unknown;49195;0,006024;\n" +
            "veritas-ucl;2148;0,005946;# Veritas Universal Communication Layer\n" +
            "unknown;41524;0,005697;\n" +
            "amanda;10080;0,005585;# Amanda Backup Util\n" +
            "sometimes-rpc8;32772;0,005352;# Sometimes an RPC port on my Solaris box (status)\n" +
            "timbuktu;407;0,005305;\n" +
            "nameserver;42;0,005288;# Host Name Server\n" +
            "unknown;33354;0,005179;\n" +
            "activesync-notify;1034;0,005173;# Windows Mobile device ActiveSync Notifications\n" +
            "unknown;49180;0,00502;\n" +
            "unknown;49199;0,00502;";

    public static final HashMap<Integer, PortData> TCPMostScannedPorts = new HashMap<>();

    public static final HashMap<Integer, PortData> UDPMostScannedPorts = new HashMap<>();

    private static void fillData(HashMap<Integer, PortData> data, String[] lines) {
        for (String line : lines) {
            String[] parts = line.split(";");
            int portNumber = Integer.parseInt(parts[1]);
            double scanFreq = Double.parseDouble(parts[2].replace(",", "."));
            if (parts.length == 4)
                data.put(portNumber, new PortData(portNumber, parts[0], scanFreq, parts[3].replace("#", "").trim()));
            else
                data.put(portNumber, new PortData(portNumber, parts[0], scanFreq, ""));
        }
    }

    static {
        fillData(TCPMostScannedPorts, TCPMostScannedPortsRawInfo.split("\n"));
        fillData(UDPMostScannedPorts, UDPMostScannedPortsRawInfo.split("\n"));
    }
}
