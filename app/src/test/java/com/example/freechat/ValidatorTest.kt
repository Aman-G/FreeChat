package com.example.freechat

import com.example.freechat.util.Validator
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorTest {

    @Test
    fun validatorEmail() {
        assertThat(Validator.validateEmail("test@gmail.com")).isTrue()
    }
}