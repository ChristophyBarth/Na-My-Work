package kodecamp.android.na_my_work.ui.data

import android.net.Uri
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kodecamp.android.na_my_work.ui.model.Notification
import kodecamp.android.na_my_work.ui.model.WorkExperience
import java.lang.reflect.ParameterizedType

class Converters {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val stringListType : ParameterizedType = Types.newParameterizedType(MutableList::class.java, String::class.java)
    private val stringListAdapter: JsonAdapter<MutableList<String?>> = moshi.adapter(stringListType)

    private val notificationMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val notificationListType: ParameterizedType = Types.newParameterizedType(MutableList::class.java, String::class.java, Notification::class.java)
    private val notificationListAdapter: JsonAdapter<MutableList<Notification>> = notificationMoshi.adapter(notificationListType)

    private val notificationObjectMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).add(StringJsonAdapter).build()
    private val notificationObjectListAdapter: JsonAdapter<Notification> = notificationObjectMoshi.adapter(Notification::class.java)

    private val workExperienceMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val workExperienceListType: ParameterizedType = Types.newParameterizedType(MutableList::class.java, WorkExperience::class.java)
    private val workExperienceListAdapter: JsonAdapter<MutableList<WorkExperience>> = workExperienceMoshi.adapter(workExperienceListType)

    private val uriListMoshi = Moshi.Builder().build()
    private val uriListType: ParameterizedType = Types.newParameterizedType(MutableList::class.java, String::class.java, Uri::class.java)
    private val uriListAdapter: JsonAdapter<MutableList<Uri>> = uriListMoshi.adapter(uriListType)

    private val uriMoshi = Moshi.Builder().add(UriAdapter).build()
    private val uriAdapter: JsonAdapter<Uri> = uriMoshi.adapter(Uri::class.java)

    @TypeConverter
    fun uriToJson(uriList: Uri?): String {
        return uriAdapter.toJson(uriList)
    }

    @TypeConverter
    fun jsonToUri(json: String): Uri? {
        return uriAdapter.fromJson(json)
    }

    @TypeConverter
    fun uriListToJson(uriList: MutableList<Uri>): String {
        return uriListAdapter.toJson(uriList)
    }

    @TypeConverter
    fun jsonToUriList(json: String): MutableList<Uri>? {
        return uriListAdapter.fromJson(json)
    }

    @TypeConverter
    fun stringListToJson(stringList: MutableList<String>): String {
        return stringList.joinToString()
    }

    @TypeConverter
    fun jsonToStringList(json: String): MutableList<String> {
        return json.split(",").toMutableList()
    }
    @TypeConverter
    fun notificationListToJson(notificationList: MutableList<Notification>): String {
        return notificationListAdapter.toJson(notificationList)
    }

    @TypeConverter
    fun jsonToNotificationList(json: String): MutableList<Notification>? {
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
    fun workExperienceListToJson(workExperienceList: MutableList<WorkExperience>): String {
        return workExperienceListAdapter.toJson(workExperienceList)
    }

    @TypeConverter
    fun jsonToWorkExperienceList(json: String): MutableList<WorkExperience>? {
        return workExperienceListAdapter.fromJson(json)
    }
}

