package models.metadata;

import com.fasterxml.jackson.databind.JsonNode;

import util.APICall;
import util.Constants;

public class UserService {
	private static final String VERIFY_USER_SINGUP = Constants.NEW_BACKEND+"home/signup";

	private static final String VERIFY_USER_CALL = Constants.NEW_BACKEND+"home/login";
	private static final String GET_ALL_USER_CALL = Constants.NEW_BACKEND+"userService/getAllUserServices/json";
	
	public static JsonNode verifyUserAuthentity(JsonNode jsonData) {
		return APICall.postAPI(VERIFY_USER_CALL, jsonData);
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

	public static JsonNode verifyUserSUAuthentity(JsonNode jsonData) {
		return APICall.postAPI(VERIFY_USER_SINGUP, jsonData);
	}

}
