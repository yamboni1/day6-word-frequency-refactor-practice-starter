import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_DELIMITER = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    private static final String SPACE_CHAR = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final int ONE = 1;

    public String getWordsWithFrequencies(String inputStr) {


        if (inputStr.split(SPACE_DELIMITER).length == ONE) {
            return inputStr + " 1";
        } else {
            try {
                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfo(inputStr);

                return generatePrintLines(wordFrequencyInfoList);
            } catch (Exception e) {

                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordFrequencyInfo> getWordFrequencyInfo(String inputStr) {
        String[] words = inputStr.split(SPACE_DELIMITER);

        List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
        for (String word : words) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(word, ONE);
            wordFrequencyInfoList.add(wordFrequencyInfo);
        }

        Map<String, List<WordFrequencyInfo>> frequencyInfoList = getListMap(wordFrequencyInfoList);

        List<WordFrequencyInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequencyInfo>> entry : frequencyInfoList.entrySet()) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
            list.add(wordFrequencyInfo);
        }
        wordFrequencyInfoList = list;

        wordFrequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return wordFrequencyInfoList;
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map(wordFrequencyInfo -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEWLINE_DELIMITER));

    }


    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> map = new HashMap<>();
        for (WordFrequencyInfo wordFrequencyInfo : wordFrequencyInfoList) {
            if (!map.containsKey(wordFrequencyInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequencyInfo);
                map.put(wordFrequencyInfo.getWord(), arr);
            } else {
                map.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        }


        return map;
    }


}
