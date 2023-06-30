package anywherecodingexercise

import com.takari.anywherecodingexercise.data.characterResponse.CharacterResponse
import retrofit2.http.GET


interface DuckDuckGoService {

    @GET("/?q=simpsons+characters&format=json")
    suspend fun getCharacters(): CharacterResponse
}
