# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

-printusage usage.txt

-keep class kotlinx.coroutines.** { *; }
-keep class kotlin.coroutines.Continuation { *; }

-dontwarn java.lang.invoke.StringConcatFactory
-dontwarn org.xmlpull.v1.**
-dontwarn org.kxml2.io.**
-dontwarn android.content.res.**
-keep class org.xmlpull.** { *; }
-keepclassmembers class org.xmlpull.** { *; }

-keep class retrofit2.Response { *; }
-keep class okhttp3.logging.HttpLoggingInterceptor { *; }

-keep class com.contraomnese.courses.design.theme.** { *; }
-keep class com.contraomnese.courses.core.design.icon.CoursesIcons
-keep class com.contraomnese.courses.core.ui.DevicePreviews

-keep class com.contraomnese.courses.presentation.architecture.** { *; }
-keep class com.contraomnese.courses.presentation.utils.** { *; }

-keep class com.contraomnese.courses.core.navigation.** { *; }

-keep class com.contraomnese.courses.core.ui.composition.** { *; }
-keep class com.contraomnese.courses.core.ui.widgets.** { *; }
-keep class com.contraomnese.courses.core.ui.utils.** { *; }

-keep class com.contraomnese.courses.data.network.** { *; }
-keep class com.contraomnese.courses.data.models.** { *; }
-keep class com.contraomnese.courses.data.repository.** { *; }
-keep class com.contraomnese.courses.data.storage.db.** { *; }
-keep class com.contraomnese.courses.data.storage.memory.** { *; }
-keep class com.contraomnese.courses.data.parsers.** { *; }
-keep class com.contraomnese.courses.data.mappers.** { *; }
-keep class com.contraomnese.courses.data.utils.** { *; }

-keep class com.contraomnese.courses.domain.** { *; }

-keep class com.contraomnese.courses.navigation.** { *; }
-keep class com.contraomnese.courses.MainActivityAction { *; }
-keep class com.contraomnese.courses.MainActivityEffect { *; }
-keep class com.contraomnese.courses.MainActivityEvent { *; }
-keep class com.contraomnese.courses.MainActivityState { *; }
-keep class com.contraomnese.courses.MainActivityViewModel { *; }

-keep class com.contraomnese.courses.features.bottom_menu.navigation.** { *; }