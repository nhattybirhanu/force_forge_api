import { Routes } from '@angular/router';
import {TaskEditorComponent} from "./task-editor/task-editor.component";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'folder/inbox',
    pathMatch: 'full',
  },
  {
    path: 'task/:id',
    pathMatch: 'full',
    component: TaskEditorComponent

  },
  {
    path: 'task',
    pathMatch: 'full',
    component: TaskEditorComponent

  },
  {
    path: 'folder/:id',
    loadComponent: () =>
      import('./folder/folder.page').then((m) => m.FolderPage),
  },
];
