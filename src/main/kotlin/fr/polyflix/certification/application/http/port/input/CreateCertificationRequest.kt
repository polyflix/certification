package fr.polyflix.certification.application.http.port.input

data class CreateCertificationRequest(
    val name: String
) {
    // An empty constructor is necessary for jackson to deserialization
    constructor(): this("")
}
