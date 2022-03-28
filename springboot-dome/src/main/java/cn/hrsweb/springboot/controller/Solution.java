package cn.hrsweb.springboot.controller;

class Solution {
    public int lengthOfLastWord(String s) {
        int length = s.length();
        int lastLen = 0;
        for(int i=length-1;i>=0;--i){
            char ch = s.charAt(i);
            if(ch==' '){
                break;
            }
            ++lastLen;
        }
        return lastLen;
    }

    public static void main(String[] args) {
        String str = "   fly me   to   the moon  ";
        String[] strings = str.split(" ");
        for (int i = 0;i < strings.length;i++){
            System.out.println(strings[i]);
        }

    }
}