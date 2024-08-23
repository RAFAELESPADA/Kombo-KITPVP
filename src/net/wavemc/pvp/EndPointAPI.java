package net.wavemc.pvp;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.var;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EndPointAPI {

	
	@Test
	public Response findtopplayers(int position) throws IOException {
	    Request request = new Request.Builder()
	      .url("170.231.121.12" + "/wavepvp_player_kills/alltime/" + position)
	      .build();
OkHttpClient client = new OkHttpClient(); 
	    Call call = client.newCall(request);
	    Response response = call.execute();
	    return response;
	}
	  
	
}
