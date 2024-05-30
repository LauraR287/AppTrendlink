package com.example.apptrendlink_valentinahernandez

class Message {
    // Getters y setters
    var senderId: String? = null
    var text: String? = null
    var timestamp: Long = 0

    constructor()

    constructor(senderId: String?, text: String?, timestamp: Long) {
        this.senderId = senderId
        this.text = text
        this.timestamp = timestamp
    }
}