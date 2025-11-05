// Script criado por spaceeinstein para o GTA VC e adaptado por Luan Alcolea / 2021
SCRIPT_START
{
LVAR_INT effect key
LVAR_FLOAT zoom zoomCar zoomValue
zoom = 120.0
effect = 0

IF NOT READ_INT_FROM_INI_FILE "cleo\RadarZoom.ini" "Config" "Key" key
   key = 84 // Key T
ENDIF

IF NOT READ_FLOAT_FROM_INI_FILE "cleo\RadarZoom.ini" "Config" "Zoom" zoomValue
   zoomValue = 350.0 // Zoom value
ENDIF

WHILE TRUE
  WAIT 10

  IF IS_KEY_PRESSED key
     timera = 0
     zoom = zoomValue
     zoomCar = 0.0
     WRITE_MEMORY 0x8E281C 4 zoom FALSE // Radar Range
     effect = 1
  ENDIF
  IF zoom >= 120.0
     IF effect = 1
        WRITE_MEMORY 0x940560 4 1 TRUE // Car name stage
        WRITE_MEMORY 0x8F29AC 4 1 TRUE // Area name stage
     ENDIF
     IF timera > 1000
        zoom -=@ 10.0
        zoomCar +=@ 10.0
        IF zoom < 120.0
           zoom = 120.0
           zoomCar = 230.0
           effect = 0
        ENDIF
     ENDIF
  ENDIF

  WRITE_MEMORY 0x4A4270 4 zoom TRUE // In car min zoom
  WRITE_MEMORY 0x4A42BA 4 zoom TRUE // On foot min zoom
  WRITE_MEMORY 0x5F7100 4 zoomCar FALSE // Fast car dynamic zoom
  WRITE_MEMORY 0x5F7108 4 zoom FALSE // Minimum fast car dynamic zoom

ENDWHILE
}
SCRIPT_END 