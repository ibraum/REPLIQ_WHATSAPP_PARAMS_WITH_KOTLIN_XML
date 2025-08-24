package tg.ibrahim.kondo.whatsapp_params

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.MaterialColors

class MainActivity : AppCompatActivity() {
    private fun setupSettingItem(view: View, tag: String) {
        val parts = tag.split("|")
        val title = parts[0]
        val description = parts[1]
        val iconRes = resources.getIdentifier(parts[2].replace("@drawable/", ""), "drawable", packageName)

        view.findViewById<TextView>(R.id.title).text = title
        view.findViewById<TextView>(R.id.description).text = description
        view.findViewById<ImageView>(R.id.icon).setImageResource(iconRes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById<Toolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        val settingsTags: Map<Int, String> = mapOf(
            R.id.keyItem          to "Compte|Notification de securite, changer de numero|@drawable/ic_key",
            R.id.lockItem         to "Confidentialite|Bloquer des contacts, messages ephemeres|@drawable/ic_lock",
            R.id.avatarItem       to "Avatar|Creer, modifier, photo de profil|@drawable/ic_face",
            R.id.favoriItem       to "Favoris|Ajouter, reorganiser, retirer|@drawable/ic_favorite",
            R.id.discussionItem   to "Discussions|Themes, fond d ecran, historique des discussions|@drawable/ic_chat",
            R.id.notificationItem to "Notifications|Sonneries des messages, groupes et appels|@drawable/ic_notifications",
            R.id.storageItem      to "Stockage et donnees|Utilisation reseau, telechargement auto.|@drawable/ic_data_usage",
            R.id.accessibilityItem to "Accessibilite|Animation|@drawable/ic_accessibility",
            R.id.languageItem     to "Langue de l' application|Francais (langue de l' appareil)|@drawable/ic_language",
            R.id.helpItem         to "Aide|Page d aide, nous contacter, Politique de confidentialite|@drawable/ic_help",
            R.id.inviteItem       to "Inviter un contact||@drawable/ic_group_add"
        )

        settingsTags.forEach { (viewId, tagString) ->
            findViewById<View>(viewId)?.apply {
                tag = tagString
                setupSettingItem(this, tagString)
            }
        }

        val arrowDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back)?.mutate()

        arrowDrawable?.setTint(
            MaterialColors.getColor(toolbar, com.google.android.material.R.attr.colorOnSurface)
        )

        toolbar.navigationIcon = arrowDrawable

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val color = MaterialColors.getColor(findViewById(R.id.topAppBar), com.google.android.material.R.attr.colorOnSurface)

        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            val icon = menuItem.icon?.mutate()
            icon?.setTint(color)
            menuItem.icon = icon
        }
        return true
    }

}