// Script criado por Luan Alcolea / 2021
SCRIPT_START
{
LVAR_INT scplayer car pCar useHydraulic

GET_PLAYER_CHAR 0 scplayer

WHILE TRUE
  WAIT 10

  IF IS_CHAR_IN_ANY_CAR scplayer
     IF TEST_CHEAT hydraulic
        STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
        GET_VEHICLE_POINTER car pCar
        useHydraulic++
        IF useHydraulic > 1
           useHydraulic = 0
        ENDIF
     ENDIF

     IF useHydraulic = TRUE
        IF NOT IS_BOAT car
           //void __thiscall CAutomobile::HydraulicControl(CAutomobile *ecx0)
           CALL_FUNCTION 0x52D4E0 1 0 (pCar) () //Por algum motivo mesmo sendo thiscall funciona assim...
        ENDIF
     ENDIF
  ENDIF

  IF IS_CHAR_ON_FOOT scplayer
     WAIT 100
     useHydraulic = FALSE
  ENDIF

ENDWHILE
}
SCRIPT_END