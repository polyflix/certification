package fr.polyflix.certification.domain.entity

import java.util.UUID

typealias UserID = UUID

data class User(val userId: UserID, val firstName: String, val lastName: String){
    companion object {}
}
