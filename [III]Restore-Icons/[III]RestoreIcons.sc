//Script Name: RestoreIcons (Luan Alcolea)
//Data: 23/02/2021
SCRIPT_START
{
LVAR_INT scplayer paint eight ammu showIconPaint showIconEight showIconAmmu
LVAR_FLOAT radarRange radius

GET_PLAYER_CHAR 0 scplayer

WHILE TRUE
  WAIT 0

  //PORTLAND
  WHILE IS_CHAR_IN_ZONE scplayer IND_ZON
        WAIT 0
        
        READ_MEMORY 0x8E281C 4 FALSE radarRange
        radius = radarRange - 10f
        radius = radarRange - 10f

        //Portland Paint
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer 925f -358f radius radius FALSE
           IF showIconPaint = TRUE
              GOSUB portlandPaint
           ENDIF
        ELSE
           REMOVE_BLIP paint
           showIconPaint = TRUE
        ENDIF
        
        //Portland Eight
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1282f -103f radius radius FALSE
           IF showIconEight = TRUE
              GOSUB portlandEight
           ENDIF
        ELSE
           REMOVE_BLIP eight
           showIconEight = TRUE
        ENDIF

        //Portland Ammu
         IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1069f -399f radius radius FALSE
            IF showIconAmmu = TRUE
               GOSUB portlandAmmu
            ENDIF
         ELSE
            REMOVE_BLIP ammu
            showIconAmmu = TRUE
         ENDIF
         
  ENDWHILE

  //STAUTON
  WHILE IS_CHAR_IN_ZONE scplayer COM_ZON
        WAIT 0

        READ_MEMORY 0x8E281C 4 FALSE radarRange
        radius = radarRange - 10f
        radius = radarRange - 10f

        //Stauton Paint
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer 379f -493f radius radius FALSE
           IF showIconPaint = TRUE
              GOSUB stautonPaint
           ENDIF
        ELSE
          REMOVE_BLIP paint
          showIconPaint = TRUE
        ENDIF

        //Stauton Eight
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer 379f -576f radius radius FALSE
           IF showIconEight = TRUE
              GOSUB stautonEight
           ENDIF
        ELSE
           REMOVE_BLIP eight
           showIconEight = TRUE
        ENDIF

        //Stauton Amuu
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer 346f -717f radius radius FALSE
           IF showIconAmmu = TRUE
              GOSUB stautonAmmu
           ENDIF
        ELSE
           REMOVE_BLIP ammu
           showIconAmmu = TRUE
        ENDIF

  ENDWHILE

  //SHORESIDE
  WHILE IS_CHAR_IN_ZONE scplayer SUB_ZON
        WAIT 0

        READ_MEMORY 0x8E281C 4 FALSE radarRange
        radius = radarRange - 10f
        radius = radarRange - 10f

        //Shoreside Paint
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1139f 34f radius radius FALSE
           IF showIconPaint = TRUE
              GOSUB shoresidePaint
           ENDIF
        ELSE
           REMOVE_BLIP paint
           showIconPaint = TRUE
        ENDIF

        //Shoreside Eight
        IF LOCATE_CHAR_ANY_MEANS_2D scplayer -1082f 55f radius radius FALSE
           IF showIconEight = TRUE
              GOSUB shoresideEight
           ENDIF
        ELSE
           REMOVE_BLIP eight
           showIconEight = TRUE
        ENDIF

  ENDWHILE

ENDWHILE

portlandPaint:

  ADD_SPRITE_BLIP_FOR_COORD 925f -358f 10f 18 paint
  showIconPaint = FALSE

RETURN

portlandEight:

  ADD_SPRITE_BLIP_FOR_COORD 1282f -103f 14f 2 eight
  showIconEight = FALSE

RETURN

portlandAmmu:

  ADD_SPRITE_BLIP_FOR_COORD 1069f -399f 15f 20 ammu
  showIconAmmu = FALSE

RETURN

stautonPaint:

  ADD_SPRITE_BLIP_FOR_COORD 379f -493f 26f 18 paint
  showIconPaint = FALSE

RETURN

stautonEight:

  ADD_SPRITE_BLIP_FOR_COORD 379f -576f 26f 2 eight
  showIconEight = FALSE

RETURN

stautonAmmu:

  ADD_SPRITE_BLIP_FOR_COORD 346f -717f 26f 20 ammu
  showIconAmmu = FALSE

RETURN

shoresidePaint:

  ADD_SPRITE_BLIP_FOR_COORD -1139f 34f 58f 18 paint
  showIconPaint = FALSE

RETURN

shoresideEight:

  ADD_SPRITE_BLIP_FOR_COORD -1082f 55f 58f 2 eight
  showIconEight = FALSE

RETURN

}
SCRIPT_END