// Script criado por Luan Alcolea / 2020
SCRIPT_START
{
LVAR_INT scplayer pScplayer car pCar var pVar c
LVAR_FLOAT x y z speed

GET_PLAYER_CHAR 0 scplayer

WHILE TRUE
WAIT 0

IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
   IF IS_KEY_PRESSED VK_KEY_F
      STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
      GET_CAR_SPEED car speed

      IF speed >= 20f

         GOSUB abrirPorta
         GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car -1.5 0.6 0f x y z
         WARP_PLAYER_FROM_CAR_TO_COORD 0 x y z
         GOSUB pular

      ENDIF
    ENDIF
ENDIF
ENDWHILE

abrirPorta:

//Pequena correção aqui, notei que o jogo travava ao pular de um carro sem porta

   GET_VEHICLE_POINTER car pCar
   //bool __userpurge CAutomobile::IsComponentPresent@<al>(int a1@<ecx>, CAutomobile *this, int a3)
   CALL_METHOD_RETURN 0x052E660 pCar 1 0 (15) (c)

IF c = 1
   //int __thiscall CAutomobile::OpenDoor(_DWORD *this, int a2, int a3, float a4)
   CALL_METHOD 0x052E750 pCar 3 0 (1f 2 15)
ELSE
  RETURN
ENDIF

RETURN

pular:

   GET_PED_POINTER scplayer pScplayer
   GET_VAR_POINTER var pVar
   //void __thiscall CPed::SetEvasiveDive(void *this, int a2, char a3)
   CALL_METHOD 0x04D33A0 pScplayer 3 0 (0 2 pVar)

RETURN

}
SCRIPT_END