package bankSchedular.controller;

import bankSchedular.engine.BankEngine;
import bankSchedular.model.LedgerEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ledger")
@RequiredArgsConstructor
public class LedgerController {

    private final BankEngine bankEngine;

    @GetMapping
    public List<LedgerEntry> getLedger() {
        return bankEngine.snapshotLedger();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCsv() {
        List<LedgerEntry> entries = bankEngine.snapshotLedger();
        String csv = "entryId,txnId,accountId,amountCents,timestamp\n" +
                entries.stream()
                        .map(e -> String.format("%s,%s,%s,%d,%s",
                                e.getEntryId(),
                                e.getTxnId(),
                                e.getAccountId(),
                                e.getAmountCents(),
                                e.getTimestamp()))
                        .collect(Collectors.joining("\n"));

        byte[] bytes = csv.getBytes(StandardCharsets.UTF_8);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "ledger.csv");
        return ResponseEntity.ok().headers(headers).body(bytes);
    }
}
