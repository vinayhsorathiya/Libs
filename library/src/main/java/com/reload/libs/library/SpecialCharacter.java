package com.reload.libs.library;

/*
 * Created by Administer on 15 September 2018.
 *
 * Copyright (C) 2018 Reload Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.HashMap;
import java.util.Map;

public class SpecialCharacter {
    private static SpecialCharacter ourInstance;

    public static SpecialCharacter getInstance() {
        if (ourInstance == null) {
            ourInstance = new SpecialCharacter();
        }
        return ourInstance;
    }

    private SpecialCharacter() {
        /*for (int i = 0; i <= 255; i++) {
            Log.d("SpecialCharacter", " " + (char) i + "  =  " + i);
        }*/
    }

    public Map<Character, Integer> getCharacter() {
        Map<Character, Integer> addCharacter = new HashMap<Character, Integer>();

        for (int i = 0; i <= 255; i++) {
            addCharacter.put((char) i, i);
        }

        /*addCharacter.put('a', 1);
        addCharacter.put('b', 2);
        addCharacter.put('c', 3);
        addCharacter.put('d', 4);
        addCharacter.put('e', 5);
        addCharacter.put('f', 6);
        addCharacter.put('g', 7);
        addCharacter.put('h', 8);
        addCharacter.put('i', 9);
        addCharacter.put('j', 10);*/
        return addCharacter;
    }
}
