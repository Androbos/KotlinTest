package ru.medyannikov.kotlintest.ui.base.toolbar

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.find
import ru.medyannikov.kotlintest.R
import ru.medyannikov.kotlintest.hide
import ru.medyannikov.kotlintest.show

class CustomToolbar(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

  val iconMenu by lazy { find<ImageView>(R.id.icon_menu) }
  val titleWrapper by lazy { find<LinearLayout>(R.id.title_wrapper) }
  val mainTitle by lazy { find<TextView>(R.id.main_text) }
  val subText by lazy { find<TextView>(R.id.sub_text) }
  val iconClose by lazy { find<ImageView>(R.id.icon_close)}

  fun showMenuAndTitle(@StringRes title: Int) {
    clearVisability()
    iconMenu.show()
    mainTitle.show()
    mainTitle.text = resources.getText(title)
  }

  fun showMenuAndSubTitle(@StringRes title: Int, @StringRes subTitle : Int){
    clearVisability()
    iconMenu.show()
    mainTitle.show()
    subText.show()
    mainTitle.text = resources.getText(title)
    subText.text = resources.getText(subTitle)
  }

  fun showTitleAndClose(@StringRes title: Int) {
    clearVisability()
    mainTitle.show()
    iconClose.show()
    mainTitle.text = resources.getText(title)
  }

  private fun clearVisability() {
    iconMenu.hide()
    //hide(titleWrapper)
    mainTitle.hide()
    subText.hide()
    iconClose.hide()
  }
}