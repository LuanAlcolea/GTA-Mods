// Script criado por Luan Alcolea / 2021
SCRIPT_START
{
  LVAR_INT scplayer car pad key i

  //char *__cdecl CPad::GetPad(CPad *this)
  CALL_FUNCTION_RETURN 0x492F60 1 1 (0) (pad)

  WRITE_MEMORY 0x49FEE2 1 0x2 FALSE // Deactive player exit car
  WRITE_MEMORY 0x4A0214 1 0x0 FALSE // Fix bug

  GET_PLAYER_CHAR 0 scplayer

  WHILE TRUE
    WAIT 0

    IF IS_CHAR_IN_ANY_CAR scplayer
       GOSUB GetExitVehicle
       IF key = TRUE
          STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
          IF IS_CAR_STOPPED car
             i = 0
             WHILE key = TRUE
               WAIT 0
               GOSUB GetExitVehicle
               IF i = 10
                  BOAT_STOP car // Yes, boat_stop...
                  SET_CHAR_OBJ_LEAVE_CAR scplayer car
                  BREAK
               ENDIF
               i++
             ENDWHILE
             SET_CHAR_OBJ_LEAVE_CAR scplayer car
          ENDIF
       ENDIF
    ENDIF

  ENDWHILE

  GetExitVehicle:
  //char __thiscall CPad::GetExitVehicle(int this)
  CALL_METHOD_RETURN 0x4935F0 pad 0 0 () (key)

  RETURN
}
SCRIPT_END