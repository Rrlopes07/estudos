Sub sbTesteRepeticao()
    
    'COLUNA B: Limpando caracteres estranhos no nome do cliente
    For Linha = 2 To 10
        'o segundo parâmetro da cells é a coluna, convertida para número
        cells(Linha,2) = replace(cells(Linha,2), "$", "")
    Next
    
End Sub