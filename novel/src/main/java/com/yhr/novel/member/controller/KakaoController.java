package com.yhr.novel.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class KakaoController {
	
	// REST API KEY : aba9e6da70ca9621f0d3c372aa2df3af
	
	// URI 하나만 설정한다면 상수로 선언
	private final static String K_CLIENT_ID = "aba9e6da70ca9621f0d3c372aa2df3af";	
	
	/**
	 * 인가코드 받기Url
	 */
	@ResponseBody
	@RequestMapping(value = "kauth.do", method = RequestMethod.GET)
	public String getKakaoAuth(String type, HttpSession session, Model model) {
		
		String kakaoUrl = KakaoController.getAuthorizationUrl(type, session);
		
		return kakaoUrl;
	}
	
	private static String getAuthorizationUrl(String type, HttpSession session) {
		
		String redirectUri = "";
		
		if(type.equals("login")) { //로그인 버튼 눌렀을때
			redirectUri = "http://localhost:8777/novel/klogin.do";
		}else if(type.equals("enroll")) { // 카카오로 시작하기 버튼을 눌렀을 때
			redirectUri = "http://localhost:8777/novel/kenroll.do";
		}
		
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?client_id=" + K_CLIENT_ID
						+ "&redirect_uri=" + redirectUri + "&response_type=code";
		
		return kakaoUrl;
	}
	
	
	/**
	 * authorize_code 권한코드
	 */
	public static JsonNode getAccessToken(String type, String authorize_code) {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID));
		
		if(type.equals("login")) {
			postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8777/novel/klogin.do"));
		}else if(type.equals("enroll")) {
			postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8777/novel/kenroll.do"));
		}
		
		postParams.add(new BasicNameValuePair("code", authorize_code));
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		
		JsonNode returnNode = null;
		
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			
			final HttpResponse response = client.execute(post);
			
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnNode;
		
	}
	
	/**
	 * @param accessToken
	 * @return
	 */
	public static JsonNode getKakaoUserInfo(JsonNode accessToken) {
		
		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		
		post.addHeader("Authorization", "Bearer " + accessToken);
		JsonNode returnNode = null;
		
		try {
			final HttpResponse response = client.execute(post);
			
			//Json형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnNode;
		
	}
	

	
	/*
	 * 
	@RequestMapping(value="/login/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
		
		String reqUrl = "https://kauth.kakao.com/oauth/authorize"
						+ "?client_id=aba9e6da70ca9621f0d3c372aa2df3af"
						+ "&redirect_uri=http://localhost:8777/login/oauth_kakao"
						+ "&response_type=code";
		
		return reqUrl;
	}

	// 카카오 연동정보 조회
	@RequestMapping(value="/login/oauth_kakao")
	public String oauthKakao(@RequestParam(value="code", required = false)String code, Model model) throws Exception {
		
		System.out.println("#########" + code);
		String access_Token = getAccessToken(code);
		System.out.println("###access_Token#### : " + access_Token);
		
		HashMap<String, Object> userInfo = getUserInfo(access_Token);
		System.out.println("###access_Token##### : " + access_Token);
		System.out.println("###userInfo#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		
		JSONObject kakaoInfo = new JSONObject(userInfo);
		model.addAttribute("kakaoInfo", kakaoInfo);
		
		
		return "redirect:/";
	}
	
	public String getAccessToken(String authorize_code) {
		
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			
			URL url = new URL(reqURL);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 ture로 설정해야함.
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=aba9e6da70ca9621f0d3c372aa2df3af"); //본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8777/login/oauth_kakao"); // 본인이 설정해 놓은 경로
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
			
			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			
			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return access_Token;
	}
	// 유저정보조회
	public HashMap<String, Object> getUserInfo(String access_Token){
		
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "http://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			
			userInfo.put("accessToken", access_Token);
			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}
	*/
}
