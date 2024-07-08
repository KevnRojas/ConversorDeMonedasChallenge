import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class TipoDeCambio {

    private String timeLastUpdateUtc;
    private String codigoBase;
    private String codigoObjetivo;
    private Map<String,Double> conversionRates;
    private Double resultadoConversion;


    public TipoDeCambio(){
    }

    public TipoDeCambio(TipoDeCambioRecord exchange){
            this.timeLastUpdateUtc = exchange.time_last_update_utc();
            this.codigoBase = exchange.base_code();
            this.conversionRates = exchange.conversion_rates();
    }

   public String getCodigoBase(){
        return codigoBase;
    }

    public String getCodigoObjetivo(){
        return codigoObjetivo;
    }

    public Map<String,Double> getConversionRates() {
        return conversionRates;
    }

    public Double getResultadoConversion(){
        return resultadoConversion;
    }


    public TipoDeCambioRecord consultar () throws IOException, InterruptedException {

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/e03401a46b3328398ec8b75a/latest/USD");
        HttpClient Client = HttpClient.newHttpClient();
        HttpRequest Request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = Client
                .send(Request,HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        Gson gson = new GsonBuilder().create();
        TipoDeCambioRecord tipoDeCambioRecord = gson.fromJson(json,TipoDeCambioRecord.class);
        return tipoDeCambioRecord;
    }

    public double exchangeTarget (double cantidad,String codigoMoneda){
        return cantidad * getConversionRates().get(codigoMoneda);
    }

    public double exchangeBack (double cantidad, String codigoMoneda) {
        return cantidad / getConversionRates().get(codigoMoneda);
    }

    public double exchangeOverflow (double cantidad, String codigoMonedaBase, String codigoMonedaDestino) {
        return cantidad * (getConversionRates().get(codigoMonedaDestino) / getConversionRates().get(codigoMonedaBase));
    }


}
