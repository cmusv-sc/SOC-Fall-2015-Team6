package models.metadata;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import util.APICall;
import util.Constants;

public class UserService {

	private static final String VERIFY_USER_CALL = Constants.NEW_BACKEND+"user/login";
	private static final String VERIFY_USER_SINGUP = Constants.NEW_BACKEND+"user/addUser";

//	private static final String GET_ALL_USER_CALL = Constants.NEW_BACKEND+"userService/getAllUserServices/json";

	private static final String GET_USERID_BY_EMAIL = Constants.NEW_BACKEND+"user/getUserIdByEmail";
	private static final String GET_USER_BY_ID = Constants.NEW_BACKEND+"user/getUser";


	public static JsonNode verifyUserAuthentity(JsonNode jsonData) {
		return APICall.postAPI(VERIFY_USER_CALL, jsonData);
	}

	public static JsonNode verifyUserSUAuthentity(JsonNode jsonData) {
		return APICall.postAPI(VERIFY_USER_SINGUP, jsonData);
	}

	public static JsonNode getUserByEmail(JsonNode jsonData) {
		JsonNode idResponse = APICall.postAPI(GET_USERID_BY_EMAIL, jsonData);
		String id = idResponse.path("userId").asText();

		ObjectNode userData = Json.newObject();
		userData.put("userId", id);

		return APICall.postAPI(GET_USER_BY_ID, userData);
	}

//	public User getUserByEmail(String email) {
//		
//		List<User> allList = getAll();
//		for (User element : allList) {
//			String elementEmail = element.getEmail();
//			if (elementEmail.equals(email))
//				return element;
//		}
//		return null;
//	}
//	
//	public static List<User> getAll() {
//
//		List<User> allUsers = new ArrayList<User>();
//
//		JsonNode climateServicesNode = APICall
//				.callAPI(GET_ALL_USER_CALL);
//
//		if (climateServicesNode == null || climateServicesNode.has("error")
//				|| !climateServicesNode.isArray()) {
//			return climateServices;
//		}
//
//		for (int i = 0; i < climateServicesNode.size(); i++) {
//			JsonNode json = climateServicesNode.path(i);
//			ClimateService newService = new ClimateService();
//			newService.setId(json.path("id").asText());
//			newService.setClimateServiceName(json.get(
//					"name").asText());
//			newService.setPurpose(json.path("purpose").asText());
//			newService.setUrl(json.path("url").asText());
//			//newService.setCreateTime(json.path("createTime").asText());
//			newService.setScenario(json.path("scenario").asText());
//			newService.setVersion(json.path("versionNo").asText());
//			newService.setRootservice(json.path("rootServiceId").asText());
//			climateServices.add(newService);
//		}
//		return climateServices;
//	}

}
