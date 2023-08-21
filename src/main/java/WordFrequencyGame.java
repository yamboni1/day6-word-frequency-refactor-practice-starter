import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_DELIMITER = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    private static final String SPACE_CHAR = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputStr) {


        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                String[] words = inputStr.split(SPACE_DELIMITER);

                List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
                for (String word : words) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, 1);
                    wordFrequencyInfoList.add(wordFrequencyInfo);
                }

                Map<String, List<WordFrequencyInfo>> map = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequencyInfo>> entry : map.entrySet()) {
                    WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordFrequencyInfo);
                }
                wordFrequencyInfoList = list;

                wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
                for (WordFrequencyInfo word : wordFrequencyInfoList) {
                    String s = word.getValue() + SPACE_CHAR + word.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        }
    }


    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            if (!map.containsKey(wordFrequencyInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getValue(), arr);
            } else {
                map.get(wordFrequencyInfo.getValue()).add(wordFrequencyInfo);
            }
        }


        return map;
    }


}
