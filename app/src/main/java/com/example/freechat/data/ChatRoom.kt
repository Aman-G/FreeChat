package com.example.freechat.data

import com.google.firebase.firestore.PropertyName

data class ChatRoom(
    @set:PropertyName("room_id")
    var roomId: String = "",

    @set:PropertyName("room_name")
    var roomName: String = "",

    @set:PropertyName("room_avatar")
    var roomAvatar: String = ""
)