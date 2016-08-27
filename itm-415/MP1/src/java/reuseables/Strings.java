/*
Copyright 2013 Brady Houseknecht

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package reusables;

/**
 * class exposing a set of reusable
 * string evaluation and manipulation
 * functions.  
 * 
 * @author Brady Houseknecht
 */
public class Strings {
    /**
     * Method used to check if a string is null or empty
     * 
     * @param param string value
     * @return boolean value indicating the state of the string
     */
    public static boolean isEmpty(String param) {
        if (param == null || param.trim().equals("")) {
            return true;
        }
        return false;
    }
    /**
     * utility method to trim string values
     * 
     * @param param string value to be trimmed
     * @return trimmed string value
     */
    public static String trimParam(String param) {
        if (isEmpty(param)) {
            return null;
        } else {
            return param.trim();
        }
    }
} // end:Strings
