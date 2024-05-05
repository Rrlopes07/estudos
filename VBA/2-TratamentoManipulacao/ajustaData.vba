Sub sbManipulaDados()

    Dim lContaLinhas As Long

    lContaLinhas = 2

    Do While Cells(lContaLinhas, 4) <> vbNullString
    
        Cells(lContaLinhas, 5) = fnAjustaData(Cells(lContaLinhas, 4))

        lContaLinhas = lContaLinhas + 1

    Loop
    
End Sub


Function fnAjustaData(pData As String) As Date

    fnAjustaData = Mid(pData, 9, 2) & "/" & Mid(pData, 6, 2) & "/" & Mid(pData, 1, 4)
    
End Function