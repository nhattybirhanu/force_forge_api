import { TestBed } from '@angular/core/testing';

import { CommonDataEditorAlertService } from './common-data-editor-alert.service';

describe('CommonDataEditorAlertService', () => {
  let service: CommonDataEditorAlertService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommonDataEditorAlertService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
