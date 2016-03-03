package analysis;

import query.VirtuosoQueryUtility;

import java.util.LinkedList;

public class RFileInitializer {
	public static void main(String[] ar) {
		new RFileInitializer();
	}

	public RFileInitializer() {
		initRFile();
	}

	public void initRFile() {

		LinkedList<RAnalysisData> dataList = new LinkedList<RAnalysisData>();

		String result;
		String tempList[];
		result = VirtuosoQueryUtility.selectRMetaData("Developer");
		
		tempList = getSubject(result);
		
		for(int i = 0; i < tempList.length;i++){
			RAnalysisData newData = new RAnalysisData();
			newData.setName(tempList[i]);
			dataList.add(newData);
		}
		
		tempList = getObject(result);
		
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setDeveloper(tempList[i]);
		}
		
		result = VirtuosoQueryUtility.selectRMetaData("FileLocation");
		tempList = getObject(result);
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setFileLocation(tempList[i]);
		}
		
		result = VirtuosoQueryUtility.selectRMetaData("RequiredData");
		tempList = getObject(result);
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setFileLocation(tempList[i]);
		}
		
		result = VirtuosoQueryUtility.selectRMetaData("RequiredType");
		tempList = getObject(result);
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setRequiredType(tempList[i]);
		}
		
		result = VirtuosoQueryUtility.selectRMetaData("ResultData");
		tempList = getObject(result);
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setResultData(tempList[i]);
		}
		
		result = VirtuosoQueryUtility.selectRMetaData("ResultType");
		tempList = getObject(result);
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setResultType(tempList[i]);
		}
		
		result = VirtuosoQueryUtility.selectRMetaData("Version");
		tempList = getObject(result);
		for(int i = 0; i < tempList.length;i++){
			dataList.get(i).setVersion(tempList[i]);
		}
	}

	private String[] getObject(String input) {
		String splitedInput[] = input.split("\n");
		
		String tempResultList[];
		String result[] = new String[splitedInput.length - 1];
		
		String temp;
		for (int i = 1; i < splitedInput.length; i++) {
			tempResultList = splitedInput[i].split(",");
			temp = tempResultList[1];

			temp = temp.substring(1, temp.length() - 1);
			result[i-1] = temp;
		}
		
		return result;
	}

	private String[] getSubject(String input) {
		String splitedInput[] = input.split("\n");
		
		String tempResultList[];
		String result[] = new String[splitedInput.length - 1];
		
		String temp;
		for (int i = 1; i < splitedInput.length; i++) {
			tempResultList = splitedInput[i].split(",");
			temp = tempResultList[0];

			temp = temp.substring(1, temp.length() - 1);
			result[i-1] = temp;
		}
		
		return result;
	}

}
