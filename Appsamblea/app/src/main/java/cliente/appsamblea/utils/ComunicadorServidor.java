package cliente.appsamblea.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Daniel on 18/05/2015.
 */
public abstract class ComunicadorServidor {
    private static boolean loginConFacebookOk;
    private static boolean todoBien;

    public static boolean registrarConFacebook (final String facebookID, final String nombre, final String apellidos, final String email){

         Thread hebraRegistroCOnFacebook = new Thread(){
            public void run(){
                JSONObject jsonobj = new JSONObject();
                try {
                    jsonobj.put("id", facebookID);
                    jsonobj.put("nombre", nombre);
                    jsonobj.put("apellidos", apellidos);
                    jsonobj.put("email", email);

                    //Preparar petici�n
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppostreq = new HttpPost("http://appsamblea-project.appspot.com/registro");

                    StringEntity se = new StringEntity(jsonobj.toString());

                    se.setContentType("application/json;charset=UTF-8");
                    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

                    httppostreq.setEntity(se);

                    //Enviar petici�n y recibir respuesta
                    HttpResponse httpresponse = httpclient.execute(httppostreq);

                    //Leer respuesta
                    String mensaje = new JSONObject(EntityUtils.toString(httpresponse.getEntity())).getString("mensaje");
                    if (mensaje.equals("existe") || mensaje.equals("creado"))
                        loginConFacebookOk =  true;
                    else loginConFacebookOk = false;


                    //Lo de abjao sirve para hacer debug de p�ginas que llegan.
                    /*BufferedReader rd = new BufferedReader(new InputStreamReader(httpresponse.getEntity().getContent()));

                    String body;
                    while ((body = rd.readLine()) != null)
                    {
                        Log.e("HttpResponse", body);
                    }*/
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        hebraRegistroCOnFacebook.start();
        //Me espero a que la hebra termine de ejecutarse para devolver el atributo.
        try {
            hebraRegistroCOnFacebook.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loginConFacebookOk;
    }

  //Se envía una petición al servidor para crear una asamblea
  public static boolean CrearAsamblea(final String nombreAsamblea,
                                      final String lugarAsamblea,
                                      final String fechaAsamblea,
                                      final String horaAsamblea,
                                      final String descripcionAsamblea,
                                      final boolean abiertaAsamblea){
     todoBien = true;

      Thread thread = new Thread() {
          public void run() {
              JSONObject jsonobj = new JSONObject();
              try{
                  HttpClient httpclient = new DefaultHttpClient();
                  HttpPost http = new HttpPost("http://appsamblea-project.appspot.com/crearAsamblea");

                  jsonobj.put("idUsuario", "");
                  jsonobj.put("nombreAsamblea",nombreAsamblea);
                  jsonobj.put("lugarAsamblea",lugarAsamblea);
                  jsonobj.put("fechaAsamblea",fechaAsamblea);
                  jsonobj.put("horaAsamblea",horaAsamblea);
                  jsonobj.put("descripcionAsamblea",descripcionAsamblea);
                  if(abiertaAsamblea){
                      jsonobj.put("abiertaAsamblea","True");
                  }
                  else{
                      jsonobj.put("abiertaAsamblea","False");
                  }
                  StringEntity se = new StringEntity(jsonobj.toString());

                  se.setContentType("application/json;charset=UTF-8");
                  se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));

                  http.setEntity(se);

                  HttpResponse respuestaHttp = httpclient.execute(http);
                  String respuesta = EntityUtils.toString(respuestaHttp.getEntity());

              }catch (Exception e){
                  e.printStackTrace();
                  todoBien = false;
              }

          }
      };
      thread.run();
      try {
          thread.join();
      } catch (InterruptedException e) {
          e.printStackTrace();
         todoBien = false;
      }
      return todoBien;
  }

}
