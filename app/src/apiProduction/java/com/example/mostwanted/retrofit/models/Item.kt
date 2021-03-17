package com.example.mostwanted.retrofit.models


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("additional_information")
    val additionalInformation: Any?,
    @SerializedName("age_max")
    val ageMax: Any?,
    @SerializedName("age_min")
    val ageMin: Any?,
    @SerializedName("age_range")
    val ageRange: Any?,
    @SerializedName("aliases")
    val aliases: List<String>?,
    @SerializedName("build")
    val build: String?,
    @SerializedName("caution")
    val caution: String?,
    @SerializedName("complexion")
    val complexion: Any?,
    @SerializedName("coordinates")
    val coordinates: List<Any>,
    @SerializedName("dates_of_birth_used")
    val datesOfBirthUsed: List<String>?,
    @SerializedName("description")
    val description: String,
    @SerializedName("details")
    val details: Any?,
    @SerializedName("eyes")
    val eyes: String?,
    @SerializedName("eyes_raw")
    val eyesRaw: String?,
    @SerializedName("field_offices")
    val fieldOffices: List<String>,
    @SerializedName("files")
    val files: List<File>,
    @SerializedName("hair")
    val hair: String?,
    @SerializedName("hair_raw")
    val hairRaw: String?,
    @SerializedName("height_max")
    val heightMax: Int?,
    @SerializedName("height_min")
    val heightMin: Int?,
    @SerializedName("@id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("languages")
    val languages: List<String>?,
    @SerializedName("legat_names")
    val legatNames: Any?,
    @SerializedName("locations")
    val locations: Any?,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("ncic")
    val ncic: String?,
    @SerializedName("occupations")
    val occupations: List<String>?,
    @SerializedName("path")
    val path: String,
    @SerializedName("person_classification")
    val personClassification: String,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("possible_countries")
    val possibleCountries: Any?,
    @SerializedName("possible_states")
    val possibleStates: Any?,
    @SerializedName("publication")
    val publication: String,
    @SerializedName("race")
    val race: String?,
    @SerializedName("race_raw")
    val raceRaw: String?,
    @SerializedName("remarks")
    val remarks: String?,
    @SerializedName("reward_max")
    val rewardMax: Int,
    @SerializedName("reward_min")
    val rewardMin: Int,
    @SerializedName("reward_text")
    val rewardText: Any?,
    @SerializedName("scars_and_marks")
    val scarsAndMarks: Any?,
    @SerializedName("sex")
    val sex: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("suspects")
    val suspects: Any?,
    @SerializedName("title")
    val title: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("warning_message")
    val warningMessage: Any?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("weight_max")
    val weightMax: Int?,
    @SerializedName("weight_min")
    val weightMin: Int?
)