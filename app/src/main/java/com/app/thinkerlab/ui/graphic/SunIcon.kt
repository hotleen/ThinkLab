package com.app.thinkerlab.ui.graphic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

val SunIcon: ImageVector
    get() {
        if (_sunIcon != null) return _sunIcon!!

        _sunIcon = ImageVector.Builder(
            name = "SunIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {

            /* ================= 中心圆（贝塞尔画法） ================= */
            path(
                fill = SolidColor(Color(0xFF3E4A59)),
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 7f)

                curveTo(9.24f, 7f, 7f, 9.24f, 7f, 12f)
                curveTo(7f, 14.76f, 9.24f, 17f, 12f, 17f)
                curveTo(14.76f, 17f, 17f, 14.76f, 17f, 12f)
                curveTo(17f, 9.24f, 14.76f, 7f, 12f, 7f)

                close()
            }

            /* ================= 光芒 ================= */
            val cx = 12f
            val cy = 12f
            val distance = 7.6f     // 与圆的间距（关键）
            val length = 2.6f
            val width = 1.6f

            repeat(8) { i ->
                val angle = Math.toRadians(i * 45.0)
                val dx = cos(angle).toFloat()
                val dy = sin(angle).toFloat()

                val mx = cx + dx * distance
                val my = cy + dy * distance

                val px = -dy * width / 2
                val py = dx * width / 2

                val hx = dx * length / 2
                val hy = dy * length / 2

                path(
                    fill = SolidColor(Color(0xFF3E4A59)),
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(mx - hx + px, my - hy + py)
                    lineTo(mx - hx - px, my - hy - py)
                    lineTo(mx + hx - px, my + hy - py)
                    lineTo(mx + hx + px, my + hy + py)
                    close()
                }
            }
        }.build()

        return _sunIcon!!
    }

private var _sunIcon: ImageVector? = null
