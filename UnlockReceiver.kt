package com.example.ayetbildirim

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class UnlockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) return
        showNextVerse(context)
    }

    private fun showNextVerse(context: Context) {
        val prefs = context.getSharedPreferences("verses", Context.MODE_PRIVATE)
        val index = prefs.getInt("index", 0)
        val verse = verses[index % verses.size]
        prefs.edit().putInt("index", (index + 1) % verses.size).apply()

        val channelId = "ayetler"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(channelId, "Ayet Bildirimleri", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Kilit ekranında kısa ayetler"
            manager.createNotificationChannel(channel)
        }

        val openIntent = Intent(context, MainActivity::class.java)
        val pending = PendingIntent.getActivity(
            context, 0, openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Bugünün ayeti")
            .setContentText(verse.text)
            .setStyle(NotificationCompat.BigTextStyle().bigText("${verse.text}\n\n${verse.reference}"))
            .setContentIntent(pending)
            .setAutoCancel(false)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        manager.notify(1001, notification)
    }

    data class Verse(val text: String, val reference: String)

    private val verses = listOf(
        Verse("Şüphesiz Allah sabredenlerle beraberdir.", "Bakara 2:153"),
        Verse("Allah hiç kimseye gücünün yettiğinden fazlasını yüklemez.", "Bakara 2:286"),
        Verse("Kalpler ancak Allah'ı anmakla huzur bulur.", "Ra'd 13:28"),
        Verse("Şüphesiz zorlukla beraber bir kolaylık vardır.", "İnşirah 94:5"),
        Verse("Rabbin seni terk etmedi ve sana darılmadı.", "Duha 93:3"),
        Verse("Kim Allah'a tevekkül ederse, O ona yeter.", "Talâk 65:3"),
        Verse("Allah iyilik yapanların mükâfatını zayi etmez.", "Tevbe 9:120"),
        Verse("Bana dua edin, size cevap vereyim.", "Mümin 40:60"),
        Verse("Allah'ın rahmetinden ümit kesmeyin.", "Zümer 39:53"),
        Verse("Şüphesiz Allah adaleti ve iyiliği emreder.", "Nahl 16:90"),
        Verse("İyilik ve takvada yardımlaşın.", "Maide 5:2"),
        Verse("Allah sizinle beraberdir.", "Enfal 8:46"),
        Verse("Allah sabredenleri sever.", "Âl-i İmrân 3:146"),
        Verse("Şüphesiz Allah tövbe edenleri sever.", "Bakara 2:222"),
        Verse("Allah'ın yardımı yakındır.", "Bakara 2:214"),
        Verse("Şüphesiz Allah'ın rahmeti iyilik yapanlara yakındır.", "A'râf 7:56"),
        Verse("Gevşemeyin, üzülmeyin; eğer inanıyorsanız üstün olan sizsiniz.", "Âl-i İmrân 3:139"),
        Verse("Allah'tan başka ilah yoktur; O diridir, kayyumdur.", "Bakara 2:255"),
        Verse("Allah dilediğine hesapsız rızık verir.", "Bakara 2:212"),
        Verse("Şüphesiz Allah verdiği sözü bozmaz.", "Âl-i İmrân 3:9"),
        Verse("Her kim Allah'a karşı gelmekten sakınırsa, Allah ona bir çıkış yolu açar.", "Talâk 65:2"),
        Verse("Şüphesiz Allah adaletle davrananları sever.", "Mâide 5:42"),
        Verse("Allah güzel davrananları sever.", "Bakara 2:195"),
        Verse("Şüphesiz Allah, kendisine karşı gelmekten sakınanlarla beraberdir.", "Nahl 16:128"),
        Verse("Allah'ın lütfu ve rahmeti olmasaydı, elbette hüsrana uğrardınız.", "Bakara 2:64"),
        Verse("Rabbinizin bağışlamasına ve genişliği göklerle yer kadar olan cennete koşun.", "Âl-i İmrân 3:133"),
        Verse("Kim Allah'a inanırsa, Allah onun kalbine doğru yolu gösterir.", "Teğâbün 64:11"),
        Verse("Şüphesiz Allah emanetleri ehline vermenizi emreder.", "Nisâ 4:58"),
        Verse("Allah size kolaylık diler, zorluk dilemez.", "Bakara 2:185"),
        Verse("Şüphesiz Allah'ın zikri en büyüktür.", "Ankebût 29:45"),
        Verse("Rabbim, benim duama karşılık ver.", "Âl-i İmrân 3:38"),
        Verse("Rabbim, bana indireceğin her hayra muhtacım.", "Kasas 28:24"),
        Verse("Şüphesiz Allah çok bağışlayandır, çok merhamet edendir.", "Nisâ 4:96"),
        Verse("O, kullarına karşı çok şefkatli ve merhametlidir.", "Şûrâ 42:19"),
        Verse("Şüphesiz Allah'ın dostlarına korku yoktur; onlar üzülmeyeceklerdir.", "Yûnus 10:62"),
        Verse("Kim Allah'a ve Resûlüne itaat ederse, işte onlar Allah'ın nimet verdiği kimselerle beraberdir.", "Nisâ 4:69"),
        Verse("İyilikle kötülük bir olmaz. Sen kötülüğü en güzel şekilde sav.", "Fussilet 41:34"),
        Verse("Affetsinler ve hoşgörsünler. Allah'ın sizi bağışlamasını sevmez misiniz?", "Nûr 24:22"),
        Verse("Şüphesiz Allah hiçbir nefse gücünün yettiğinden fazlasını yüklemez.", "Bakara 2:286"),
        Verse("Allah'a güven. Vekil olarak Allah yeter.", "Ahzâb 33:3"),
        Verse("Şüphesiz Allah, kendisine güvenenleri sever.", "Âl-i İmrân 3:159"),
        Verse("Sabret. Şüphesiz güzel sonuç Allah'a karşı gelmekten sakınanlarındır.", "Hûd 11:49"),
        Verse("Şüphesiz insan için kendi çalışmasından başkası yoktur.", "Necm 53:39"),
        Verse("Kim zerre kadar hayır işlerse onu görür.", "Zilzâl 99:7"),
        Verse("Kim zerre kadar kötülük işlerse onu görür.", "Zilzâl 99:8"),
        Verse("Şüphesiz Allah yapılanları hakkıyla görür.", "Mücâdele 58:1"),
        Verse("Allah'ın lütfuyla ve rahmetiyle, yalnız bununla sevinsinler.", "Yûnus 10:58"),
        Verse("De ki: Allah'ın lütfu ve rahmetiyle, işte bununla sevinsinler.", "Yûnus 10:58"),
        Verse("Allah'ın ipine hep birlikte sımsıkı sarılın ve ayrılığa düşmeyin.", "Âl-i İmrân 3:103"),
        Verse("Müminler ancak kardeştir.", "Hucurât 49:10"),
        Verse("Şüphesiz Allah adaleti, iyiliği ve yakınlara yardım etmeyi emreder.", "Nahl 16:90"),
        Verse("Anne babaya güzel davranın.", "İsrâ 17:23"),
        Verse("Yetimin malına, erginlik çağına erişinceye kadar en güzel şeklin dışında yaklaşmayın.", "İsrâ 17:34"),
        Verse("Verdiğiniz sözü yerine getirin; çünkü verilen söz sorumluluk doğurur.", "İsrâ 17:34"),
        Verse("Yeryüzünde böbürlenerek yürüme.", "İsrâ 17:37"),
        Verse("Şüphesiz Allah kibirlenenleri sevmez.", "Nahl 16:23"),
        Verse("Güzel söz söyleyin.", "Bakara 2:83"),
        Verse("İnsanlara güzel söz söyleyin.", "Bakara 2:83"),
        Verse("Şüphesiz Allah iyilik yapanların ecrini zayi etmez.", "Tevbe 9:120"),
        Verse("Allah'a karşı gelmekten sakının ve doğru söz söyleyin.", "Ahzâb 33:70"),
        Verse("Ey iman edenler! Allah'ı çokça zikredin.", "Ahzâb 33:41"),
        Verse("Sabır ve namazla Allah'tan yardım isteyin.", "Bakara 2:153"),
        Verse("Şüphesiz Allah sabredenlerle beraberdir.", "Enfâl 8:46"),
        Verse("Namazı dosdoğru kılın ve zekâtı verin.", "Bakara 2:43"),
        Verse("Beni anın ki ben de sizi anayım.", "Bakara 2:152"),
        Verse("Şükrederseniz elbette size nimetimi artırırım.", "İbrâhîm 14:7"),
        Verse("Rabbim! Bana ve anne babama verdiğin nimete şükretmemi nasip et.", "Neml 27:19"),
        Verse("Rabbimiz! Bize dünyada da iyilik ver, ahirette de iyilik ver.", "Bakara 2:201"),
        Verse("Rabbimiz! Bizi bağışla, bize merhamet et.", "Müminûn 23:109"),
        Verse("Rabbimiz! Bize katından rahmet ver ve işimizde bize doğruyu kolaylaştır.", "Kehf 18:10"),
        Verse("Rabbim! Gönlüme genişlik ver.", "Tâhâ 20:25"),
        Verse("Rabbim! İlmimi artır.", "Tâhâ 20:114"),
        Verse("Rabbim! Beni yalnız bırakma.", "Enbiyâ 21:89"),
        Verse("Rabbim! Bana hayırlı bir nesil bağışla.", "Âl-i İmrân 3:38"),
        Verse("Rabbimiz! Bizi doğru yola ilettikten sonra kalplerimizi saptırma.", "Âl-i İmrân 3:8"),
        Verse("Rabbimiz! Unutur veya yanılırsak bizi sorumlu tutma.", "Bakara 2:286"),
        Verse("Rabbimiz! Bize gücümüzün yetmeyeceği yükü yükleme.", "Bakara 2:286"),
        Verse("Allah hiç şüphesiz çok bağışlayıcıdır.", "Nisâ 4:23"),
        Verse("Şüphesiz Allah'ın rahmeti her şeyi kuşatmıştır.", "A'râf 7:156"),
        Verse("Allah dilediğini doğru yola iletir.", "Bakara 2:213"),
        Verse("Allah dilediğine hikmet verir.", "Bakara 2:269"),
        Verse("Kime hikmet verilmişse ona gerçekten çok hayır verilmiştir.", "Bakara 2:269"),
        Verse("Şüphesiz Allah sözlerin en güzelini indirdi.", "Zümer 39:23"),
        Verse("Allah göklerin ve yerin nurudur.", "Nûr 24:35"),
        Verse("Allah her şeyi hakkıyla bilendir.", "Bakara 2:282"),
        Verse("Allah her şeyi görmektedir.", "Hadîd 57:4"),
        Verse("Allah her şeye gücü yetendir.", "Bakara 2:20"),
        Verse("O, her an yeni bir iştedir.", "Rahmân 55:29"),
        Verse("Şüphesiz Allah'ın vaadi gerçektir.", "Yûnus 10:55"),
        Verse("Allah en hayırlı koruyucudur ve O merhametlilerin en merhametlisidir.", "Yûsuf 12:64"),
        Verse("Şüphesiz benim Rabbim duaları işitendir.", "İbrâhîm 14:39"),
        Verse("Şüphesiz Rabbim çok merhametlidir, çok sevendir.", "Hûd 11:90"),
        Verse("Rabbim çok bağışlayıcıdır, çok merhametlidir.", "Kehf 18:58"),
        Verse("Şüphesiz Allah'ın dostlarına korku yoktur.", "Yûnus 10:62"),
        Verse("Allah, iman edenlerin dostudur.", "Bakara 2:257"),
        Verse("Allah zulmedenleri sevmez.", "Âl-i İmrân 3:57"),
        Verse("Şüphesiz Allah, adaletle hükmedenleri sever.", "Mâide 5:42"),
        Verse("İyiliğin karşılığı iyilikten başka bir şey midir?", "Rahmân 55:60"),
        Verse("Rabbinizin hangi nimetlerini yalanlayabilirsiniz?", "Rahmân 55:13"),
        Verse("Şüphesiz Allah'ın yardımı yakındır.", "Bakara 2:214"),
        Verse("Şüphesiz Allah bize yeter, O ne güzel vekildir.", "Âl-i İmrân 3:173"),
        Verse("Allah bize yeter; O ne güzel vekildir.", "Âl-i İmrân 3:173"),
        Verse("Allah'ın rahmetinden ancak inkârcılar ümit keser.", "Yûsuf 12:87"),
        Verse("Gevşemeyin ve üzülmeyin.", "Âl-i İmrân 3:139"),
        Verse("Allah'a tevekkül et; şüphesiz sen apaçık hak üzeresin.", "Neml 27:79"),
        Verse("Kim Allah'tan sakınırsa, Allah ona bir çıkış yolu gösterir.", "Talâk 65:2"),
        Verse("Kim Allah'a tevekkül ederse, O ona yeter.", "Talâk 65:3"),
        Verse("Allah'ın emri mutlaka yerine gelecektir.", "Talâk 65:1"),
        Verse("Her zorlukla beraber bir kolaylık vardır.", "İnşirah 94:6"),
        Verse("Öyleyse boş kaldığında hemen başka bir işe koyul.", "İnşirah 94:7"),
        Verse("Ve yalnız Rabbine yönel.", "İnşirah 94:8"),
        Verse("Şüphesiz sen yüce bir ahlâk üzeresin.", "Kalem 68:4"),
        Verse("Şüphesiz Allah, iyilik yapanlarla beraberdir.", "Ankebût 29:69"),
        Verse("Bizim uğrumuzda çaba gösterenleri elbette yollarımıza ileteceğiz.", "Ankebût 29:69"),
        Verse("Allah'tan sakının ve bilin ki Allah sakınanlarla beraberdir.", "Bakara 2:194"),
        Verse("Allah'a güvenip dayananlara Allah yeter.", "Zümer 39:38"),
        Verse("Allah dilediğine rahmetini tahsis eder.", "Bakara 2:105"),
        Verse("Şüphesiz Allah'ın lütfu büyüktür.", "Cuma 62:4"),
        Verse("Allah'ın lütfundan isteyin.", "Cuma 62:10"),
        Verse("Allah'ı çok zikredin ki kurtuluşa eresiniz.", "Cuma 62:10"),
        Verse("Allah'a ve Resûlüne itaat edin ki merhamet olunasınız.", "Âl-i İmrân 3:132"),
        Verse("Allah'ın rahmetinden ümit kesmeyin.", "Zümer 39:53"),
    )
}
