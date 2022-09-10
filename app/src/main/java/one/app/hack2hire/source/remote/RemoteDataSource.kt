package one.app.hack2hire.source.remote

import one.app.hack2hire.model.ShowsModel
import one.app.hack2hire.model.StructuredBooking
import one.app.hack2hire.singleton.RetrofitSetup
import retrofit2.Call

class RemoteDataSource {
    fun getShows(): Call<StructuredBooking>{
        return RetrofitSetup.gitHubService.getShows()
    }
}
