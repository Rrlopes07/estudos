Option Explicit

Sub sbManipulaDados()

    Dim rCelula            As Range
    Dim lContaLinhaDestino As Long
    Dim sPlanilhaOrigem    AS String

    lContaLinhaDestino = 2
    sPlanilhaOrigem = ActiveSheet.Name

    sbVerificarPlanilha

    'A sbVerificarPlanilha muda a planilha ativa
    Sheets(sPlanilhaOrigem).Select

    For Each rCelula in Selection

        If rCelula.Column = 4 Then
            sheets("Versão Final").cells(lContaLinhaDestino, rCelula.Column) = fnAjustaData(rCelula)
            lContaLinhaDestino = lContaLinhaDestino + 1
        Else
            sheets("Versão Final").cells(lContaLinhaDestino, rCelula.Column) = rCelula
        EndIf

    Next

    sbFormataVersaoFinal
    
End Sub


Private Sub sbFormataVersaoFinal()

    Sheets("Versão Final").Select

    Columns("A:A").EntireColumn.AutoFit
    Columns("B:B").EntireColumn.AutoFit
    Columns("C:C").EntireColumn.AutoFit
    Columns("D:D").EntireColumn.AutoFit
    
    Columns("C:C").Select

    Selection.NumberFormat = _
        "_-[$R$-pt-BR] * #,##0.00_-;-[$R$-pt-BR] * #,##0.00_-;_-[$R$-pt-BR] * ""-""??_-;_-@_-"
    
    Rows("1:1").Select
    
    Selection.Font.Bold = True
    
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = False
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    
    ActiveWindow.Zoom = 160

    Range("A1").Select

    Range(Selection, Selection.End(xlDown)).Select
    Range(Selection, Selection.End(xlToRight)).Select

    Application.CutCopyMode = False

On Error Resume Next
    ActiveSheet.ListObjects.Add(xlSrcRange, Selection, , xlYes).Name = _
        "Tabela1"
    
End Sub


Function fnAjustaData(pData As String) As Date

    fnAjustaData = Mid(pData, 9, 2) & "/" & Mid(pData, 6, 2) & "/" & Mid(pData, 1, 4)
    
End Function