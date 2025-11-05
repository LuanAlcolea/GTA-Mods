// Script feito por Luan Alcolea
SCRIPT_START
{
  LVAR_INT scplayer car obj bActive showInfos i bMouseWheel
  LVAR_FLOAT x y z fPointCamX fPointCamY fPointCamZ fCurSpeed fAddSpeed

  GET_PLAYER_CHAR 0 scplayer

  REQUEST_MODEL 1360
  LOAD_ALL_MODELS_NOW
  MARK_MODEL_AS_NO_LONGER_NEEDED 1360
 
  fCurSpeed = 1.0f
  fAddSpeed = 0.3f
  showInfos = TRUE

  WHILE TRUE
    WAIT 0
    
    IF NOT IS_PLAYER_DEAD 0
       GOSUB checkIsActive
       IF bActive = TRUE
          GET_CHAR_COORDINATES scplayer x y z
          CLEO_CALL createObject 0 (obj x y z) (obj)
          CLEO_CALL disableStuffs 0 (1, scplayer) ()
          i = TRUE
          WHILE bActive = TRUE
            WAIT 0

            GOSUB checkIsActive
            IF IS_KEY_PRESSED VK_KEY_W
               READ_MEMORY 0x6FACFC 4 FALSE fPointCamX
               READ_MEMORY 0x6FAD00 4 FALSE fPointCamY
               READ_MEMORY 0x6FAD14 4 FALSE fPointCamZ
	            fPointCamX *= fCurSpeed
	            fPointCamY *= fCurSpeed
	            fPointCamZ *= fCurSpeed
               fPointCamX *= -1.0
	            fPointCamY *= 1.0
	            fPointCamZ *= 1.0
	            GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS obj fPointCamY fPointCamX fPointCamZ x y z
	            SET_OBJECT_COORDINATES obj x y z
	            SET_CHAR_COORDINATES scplayer x y z   
            ENDIF
	         IF IS_KEY_PRESSED VK_KEY_S
               READ_MEMORY 0x6FACFC 4 FALSE fPointCamX
               READ_MEMORY 0x6FAD00 4 FALSE fPointCamY
               READ_MEMORY 0x6FAD14 4 FALSE fPointCamZ
	            fPointCamX *= fCurSpeed
	            fPointCamY *= fCurSpeed
	            fPointCamZ *= fCurSpeed
	            fPointCamX *= 1.0
	            fPointCamY *= -1.0
	            fPointCamZ *= -1.0
	            GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS obj fPointCamY fPointCamX fPointCamZ x y z
	            SET_OBJECT_COORDINATES obj x y z
	            SET_CHAR_COORDINATES scplayer x y z
            ENDIF
            IF IS_KEY_PRESSED VK_KEY_Q
	            fPointCamZ = fCurSpeed
	            fPointCamZ *= -1.0
	            GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS obj 0f 0f fPointCamZ x y z
	            SET_OBJECT_COORDINATES obj x y z
	            SET_CHAR_COORDINATES scplayer x y z
	         ENDIF
	         IF IS_KEY_PRESSED VK_KEY_E
	            fPointCamZ = fCurSpeed
	            fPointCamZ *= 1.0
	            GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS obj 0f 0f fPointCamZ x y z
	            SET_OBJECT_COORDINATES obj x y z
	            SET_CHAR_COORDINATES scplayer x y z
	         ENDIF
            IF IS_KEY_PRESSED VK_PRIOR
               GOSUB addSpeed
            ENDIF
            IF IS_KEY_PRESSED VK_NEXT
               GOSUB subtractSpeed
            ENDIF
            CLEO_CALL getMouseWheel 0 (bMouseWheel)
            IF bMouseWheel = 1
               GOSUB addSpeed
            ENDIF
            IF bMouseWheel = 2
               GOSUB subtractSpeed
            ENDIF
            
            IF showInfos = TRUE
                  PRINT_FORMATTED_NOW "X: %.3f Y: %.3f Z: %.3f Speed: %.3f" 9999 x y z fCurSpeed
               ENDIF
           ENDWHILE
       ELSE
           IF i = TRUE
              CLEO_CALL disableStuffs 0 (0, scplayer) ()
              DELETE_OBJECT obj
              CLEAR_PRINTS
              i = FALSE
           ENDIF
       ENDIF
    ENDIF
  ENDWHILE

  checkIsActive:
  IF TEST_CHEAT noc
     bActive += 1
     IF bActive > 1
        bActive = 0
     ENDIF
  ENDIF

  RETURN
  
  addSpeed:
  fCurSpeed +=@ fAddSpeed

  RETURN

  subtractSpeed:
  IF fCurSpeed > 0.0
     fCurSpeed -=@ fAddSpeed
  ELSE
     fCurSpeed = 0.0 
  ENDIF

  RETURN
}
SCRIPT_END
{
   LVAR_INT bDisable scplayer // In
   LVAR_INT car pCar pPed iUseCollision iUseGravity

   disableStuffs:

   IF bDisable = TRUE
      iUseCollision = 11010000 // 11010000  208  
	   iUseGravity = 4
      WRITE_MEMORY 0x492724 1 0x0 TRUE //  Disable pads update
      WRITE_MEMORY 0x4D0C05 1 0x1 TRUE // Disable check in the air
   ELSE
      iUseGravity = 2
	   iUseCollision = 11010001 // 11010001  211
      WRITE_MEMORY 0x492724 1 0x1 TRUE //Enable pads update
      WRITE_MEMORY 0x4D0C05 1 0x0 TRUE // Enable check in the air
   ENDIF

   IF IS_CHAR_IN_ANY_CAR scplayer
      STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
      GET_VEHICLE_POINTER car pCar
      pCar += 0x51 // CEntity.m_bFlags1
      iUseCollision = 193
      WRITE_MEMORY pCar 1 iUseCollision TRUE

      GET_VEHICLE_POINTER car pCar
	   pCar += 0x122 // CPhysical.m_bFlags
	   WRITE_MEMORY pCar 1 iUseGravity TRUE
   ELSE
      GET_PED_POINTER scplayer pPed
	   pPed += 0x51
	   WRITE_MEMORY pPed 1 iUseCollision TRUE

	   GET_PED_POINTER scplayer pPed
	   pPed += 0x122
	   WRITE_MEMORY pPed 1 iUseGravity TRUE
   ENDIF

   CLEO_RETURN 0 ()
}
{
   LVAR_INT obj //In
   LVAR_FLOAT x y z //In

   createObject:

   CREATE_OBJECT 1360 x y z obj
   SET_OBJECT_COLLISION obj 0
   SET_OBJECT_HEADING obj 0.0
   SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE x y z 5f 1360 FALSE

   CLEO_RETURN 0 (obj) // Just to fix bugs
}
{
   LVAR_INT bMouseWheel

   getMouseWheel:

   READ_MEMORY 0x6F1E63 1 FALSE bMouseWheel
   IF bMouseWheel = TRUE
      CLEO_RETURN 0 (1)
   ENDIF
   READ_MEMORY 0x6F1E64 1 FALSE bMouseWheel
   IF bMouseWheel = TRUE
      CLEO_RETURN 0 (2)
   ENDIF

   CLEO_RETURN 0 (bMouseWheel)
}