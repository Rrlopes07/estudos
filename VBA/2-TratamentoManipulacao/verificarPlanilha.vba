Private Sub sbVerificarPlanilha()

    Dim wPlanilha As Worksheet
    Dim bExiste As Boolean
    
    bExiste = False
    
    For Each wPlanilha In ThisWorkbook.Worksheets

        If wPlanilha.Name <> "Versão Final" Then
            bExiste = True
            Exit For
        End If

    Next

    If Not bExiste Then
        ThisWorkbook.Worksheets.Add.Name = "Versão Final"

        With Sheets("Versão Final")
            .Cells(1, 1).Value = "Código de Cliente"
            .Cells(1, 2).Value = "Tipo de Movimentação"
            .Cells(1, 3).Value = "Valor"
            .Cells(1, 4).Value = "Data"
        End With
    End If

End Sub
