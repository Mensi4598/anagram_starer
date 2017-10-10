/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    HashSet<String> WorldList1 = new HashSet<>();
    HashMap<String, ArrayList> stringArrayListHashMap = new HashMap<String, ArrayList>();
    ArrayList<String> WordList = new ArrayList<String>();
    ;

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);

        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            WordList.add(word);
            WorldList1.add(word);
            String sortedWord = SortLetter(word);
            if (stringArrayListHashMap.containsKey(sortedWord)) {
                ArrayList<String> tmp = stringArrayListHashMap.get(sortedWord);
                tmp.add(word);
                stringArrayListHashMap.put(sortedWord, tmp);
            } else {
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(word);
                stringArrayListHashMap.put(sortedWord, tmp);
            }
            // result.put(targetWord.toString(),WordList.get(i));
        }

    }

    public boolean isGoodWord(String word, String base) {


        if (!word.contains(base) && WorldList1.contains(word)) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAnagrams(String targetWord) {
        //   public HashMap<String ,String> getAnagrams(String targetWord) {

        String sc = SortLetter(targetWord);/*
        ArrayList<String> result = new ArrayList<String>();


        for (int i=0;i<WordList.size();i++)
        {
            if(WordList.get(i).length()==targetWord.length()){
                String Wtmp=WordList.get(i).toString();
               if(SortLetter(Wtmp).equals(sc)){
                    result.add(WordList.get(i));


              }

            }

        }*/

        return stringArrayListHashMap.get(sc);
    }

    public String SortLetter(String str) {
        char[] x = str.toCharArray();
        Arrays.sort(x);

     /*       for(int i=0;i<str.length();i++){
                for(int j=i;j<str.length();j++){

            }*/

        return Arrays.toString(x);

    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for (char chr = 'a'; chr <= 'z'; chr++) {
            ArrayList<String> anagram = new ArrayList<>();
            String temp = word;
            temp += chr;

            temp = SortLetter(temp);

            if (stringArrayListHashMap.containsKey(temp)) {
                anagram = stringArrayListHashMap.get(temp);
            }
            for (int i = 0; i < anagram.size(); i++) {
                if (isGoodWord(anagram.get(i), word)) {
                    result.add(anagram.get(i));
                }
            }
        }
        return result;
    }

    public String pickGoodStarterWord() {

        int min = 0;
        int max = WorldList1.size();
        Random random = new Random();
        int RandomIndex = random.nextInt(max - min + 1) + min;
                for (int i = RandomIndex; i < WorldList1.size(); i++) {
            String tmp = WordList.get(i);
            tmp = SortLetter(tmp);
            if (stringArrayListHashMap.containsKey(tmp)) {
                ArrayList<String> strings = stringArrayListHashMap.get(tmp);
                if (strings.size() >= MIN_NUM_ANAGRAMS) {
                    return WordList.get(i);

                }

            }

        }

        return "stop";
    }

}
