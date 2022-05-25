package com.mm.domain.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class LoginUtilTest{

    @Test
    fun `empty username returns false` (){
        val result = LoginUtil.validateLoginInput(
            "",
            "abc"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false` (){
        val result = LoginUtil.validateLoginInput(
            "mudit",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `email and password exists return true` (){
        val result = LoginUtil.validateLoginInput(
            "mudit.m@tcs.com",
            "Tcs@12345"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `email and wrong password exists return true` (){
        val result = LoginUtil.validateLoginInput(
            "mudit14june@gmail.com",
            "Tcs@12345"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `email and right password exists return true` (){
        val result = LoginUtil.validateLoginInput(
            "mudit14june@gmail.com",
            "Tcs@1234"
        )
        assertThat(result).isTrue()
    }



}