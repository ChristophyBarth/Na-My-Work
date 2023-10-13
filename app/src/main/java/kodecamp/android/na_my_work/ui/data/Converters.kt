package kodecamp.android.na_my_work.ui.data

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kodecamp.android.na_my_work.ui.model.Notification
import kodecamp.android.na_my_work.ui.model.WorkExperience
import java.lang.reflect.ParameterizedType
import java.net.URI

class Converters {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val stringListType : ParameterizedType = Types.newParameterizedType(List::class.java, String::class.java)
    private val stringListAdapter: JsonAdapter<List<String?>> = moshi.adapter(stringListType)

    private val notificationMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val notificationListType: ParameterizedType = Types.newParameterizedType(List::class.java, String::class.java, Notification::class.java)
    private val notificationListAdapter: JsonAdapter<List<Notification>> = notificationMoshi.adapter(notificationListType)

    private val notificationObjectMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).add(StringJsonAdapter).build()
    private val notificationObjectListAdapter: JsonAdapter<Notification> = notificationObjectMoshi.adapter(Notification::class.java)

    private val workExperienceMoshi = Moshi.Builder().build()
    private val workExperienceListType: ParameterizedType = Types.newParameterizedType(List::class.java, String::class.java, WorkExperience::class.java)
    private val workExperienceListAdapter: JsonAdapter<List<WorkExperience>> = workExperienceMoshi.adapter(workExperienceListType)

    private val uriListMoshi = Moshi.Builder().build()
    private val uriListType: ParameterizedType = Types.newParameterizedType(List::class.java, String::class.java, URI::class.java)
    private val uriListAdapter: JsonAdapter<List<URI>> = uriListMoshi.adapter(uriListType)

    private val uriMoshi = Moshi.Builder().add(URIAdapter).build()
    private val uriAdapter: JsonAdapter<URI> = uriMoshi.adapter(URI::class.java)

    @TypeConverter
    fun uriToJson(uriList: URI?): String {
        return uriAdapter.toJson(uriList)
    }

    @TypeConverter
    fun jsonToUri(json: String): URI? {
        return uriAdapter.fromJson(json)
    }

    @TypeConverter
    fun uriListToJson(uriList: List<URI>): String {
        return uriListAdapter.toJson(uriList)
    }

    @TypeConverter
    fun jsonToUriList(json: String): List<URI>? {
        return uriListAdapter.fromJson(json)
    }

    @TypeConverter
    fun stringListToJson(stringList: List<String>): String {
        return stringList.joinToString()
    }

    @TypeConverter
    fun jsonToStringList(json: String): List<String> {
        return json.split(",")
    }
    @TypeConverter
    fun notificationListToJson(notificationList: List<Notification>): String {
        return notificationListAdapter.toJson(notificationList)
    }

    @TypeConverter
    fun jsonToNotificationList(json: String): List<Notification>? {
        return notificationListAdapter.fromJson(json)
    }

    @TypeConverter
    fun notificationObjectListToJson(notificationObjectList: Notification?): String {
        return notificationObjectListAdapter.toJson(notificationObjectList)
    }

    @TypeConverter
    fun jsonToNotificationObjectList(json: String): Notification? {
        return notificationObjectListAdapter.fromJson(json)
    }

    @TypeConverter
    fun workExperienceListToJson(workExperienceList: List<WorkExperience>): String {
        return workExperienceListAdapter.toJson(workExperienceList)
    }

    @TypeConverter
    fun jsonToWorkExperienceList(json: String): List<WorkExperience>? {
        return workExperienceListAdapter.fromJson(json)
    }
}

