package YccStudio;

import java.util.Date;
import java.util.Map;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class apnsDemo {

    public static void main(String[] args) {
                
        String certFilePath="D:/SenaoECBackendWorkspace/prod_appsvc_apache-tomcat-7.0.85/webapps/prod_appsvc/apps_cert_files/SPLUS2R.p12";
        String certPassword="qwer1234";
        String deviceToken="375c78f0411c3246ffcc85faae33438a080a854ad131ba55df9709fc259020ed";
        
        
        ApnsService service=APNS.newService().withCert(certFilePath, certPassword).withProductionDestination().build();
        String payload = APNS.newPayload().alertBody("YenCheChang Demo").build();
        com.notnoop.apns.ApnsNotification an=service.push(deviceToken, payload);       
        
        
        //To query the feedback service for inactive devices
        Map<String, Date> inactiveDevices = service.getInactiveDevices();
        for (String theDeviceToken : inactiveDevices.keySet()) {
            Date inactiveAsOf = inactiveDevices.get(theDeviceToken);
            System.out.println("DeviceToken="+theDeviceToken+" "+inactiveAsOf);
        }
        
        
        String payload2 = APNS.newPayload().badge(3).
            alertBody("測試").        
            customField("secret", "this is demo").
            localizedKey("GAME_PLAY_REQUEST_FORMAT").
            localizedArguments("ROSSI", "YCC").
            actionKey("Play").build();
        service.push(deviceToken, payload2);
        
        
        com.notnoop.apns.PayloadBuilder p=APNS.newPayload();
        String soundFilePath="C:/Windows/Media/Ring05.wav";
        p=p.sound(soundFilePath);
        String alertMessage="YenCheChang 1000RR";
        p=p.alertBody(alertMessage);
        String launchImageFilePath="D:/TEST.gif";
        p=p.launchImage(launchImageFilePath);
        p=p.badge(3);
        service.push(deviceToken, p.build());
    }
    
    

}
