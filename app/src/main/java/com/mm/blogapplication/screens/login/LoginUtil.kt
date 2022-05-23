package com.mm.blogapplication.screens.login

object LoginUtil {

    private val existingUsers = mapOf("mudit.m@tcs.com" to "Tcs@12345", "mudit14june@gmail.com" to "Tcs@1234")

    /**

     * The input is not valid if..
     * ....the username/Password is empty
     * .... user is not found
     */

    fun validateLoginInput(
        userName : String,
    password: String): Boolean{
       if(userName.isEmpty() || password.isEmpty()){
           return false
       }
       if(userName !in existingUsers){
           return false
       }
        if(userName in existingUsers){
            if(existingUsers.getValue(userName) != password){
                return false
            }
        }
        return true
    }

}